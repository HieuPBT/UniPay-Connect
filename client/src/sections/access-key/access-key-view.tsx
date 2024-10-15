'use client'

import { IconButton, Paper, Stack, Table, TableBody, Tooltip } from "@mui/material";
import { TablePagination } from "@mui/material";
import { Box, Button, Card, CardContent, CardHeader, TableCell, TableContainer, TableHead, TableRow, TableSortLabel } from "@mui/material";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import BigNumber from "bignumber.js";
import { useMemo, useState } from "react";
import { getCurrentUserAccessKeys } from "src/api/getCurrentUserAccessKey";
import { DashboardContent } from "src/layouts/dashboard";
import { IAccessKeyModel } from "./types/access-key";
import { Iconify } from "src/components/iconify";
import { Label } from "src/components/label";
import { usePopover } from "src/components/custom-popover";
import { useBoolean } from "src/hooks/use-boolean";
import AccessKeyModal from "./access-key-modal";
import { revokedAccessKey } from "src/api/revokedAccessKey";
import { addAccessKey } from "src/api/addAccessKey";

const ROWS_PER_PAGE_OPTIONS = [10, 25, 50]
type Order = 'asc' | 'desc'

interface HeadCell {
    id: keyof IAccessKeyModel;
    label: string;
    numberic: boolean;
    sortable: boolean;
}

const headCells: HeadCell[] = [
    { id: 'id', numberic: true, label: 'ID', sortable: true },
    { id: 'apiKey', numberic: true, label: 'API KEY', sortable: true },
    { id: 'status', numberic: false, label: 'Status', sortable: true },
    { id: 'expires_at', numberic: false, label: 'Expires At', sortable: true },
    { id: 'revoked_at', numberic: false, label: 'Revoked At', sortable: true },
    { id: 'created_at', numberic: false, label: 'Created At', sortable: true },
];

function EnhancedTableHead(props: {
    onRequestSort: (event: React.MouseEvent<unknown>, property: keyof IAccessKeyModel) => void;
    order: Order;
    orderBy: string;
}) {
    const { order, orderBy, onRequestSort } = props;
    const createSortHandler = (property: keyof IAccessKeyModel) => (event: React.MouseEvent<unknown>) => {
        onRequestSort(event, property);
    };

    return (
        <TableHead>
            <TableRow>
                {headCells.map((headCell) => (
                    <TableCell
                        key={headCell.id}
                        // align={headCell.numeric ? 'right' : 'left'}
                        sortDirection={orderBy === headCell.id ? order : false}
                    >
                        {headCell.sortable ? (
                            <TableSortLabel
                                active={orderBy === headCell.id}
                                direction={orderBy === headCell.id ? order : 'asc'}
                                onClick={createSortHandler(headCell.id)}
                            >
                                {headCell.label}
                            </TableSortLabel>
                        ) : (
                            headCell.label
                        )}

                    </TableCell>
                ))}
                <TableCell />
            </TableRow>
        </TableHead>
    );
}

export default function AccessKeyView() {
    const [page, setPage] = useState<number>(0);
    const [rowsPerPage, setRowsPerPage] = useState<number>(ROWS_PER_PAGE_OPTIONS[0]);
    const [totalPages, setTotalPages] = useState<number>(0);

    const [orderBy, setOrderBy] = useState<keyof IAccessKeyModel>('id');
    const [order, setOrder] = useState<Order>('asc');

    const queryClient = useQueryClient();

    const { data: accKeys, isLoading, isError } = useQuery({
        queryKey: ['accKey'],
        queryFn: async () => {
            const param = {
                page,
                size: rowsPerPage
            };
            const res = await getCurrentUserAccessKeys(param);
            setTotalPages(res.result.total);
            return res.result;
        }
    });

    const handlePageChange = (event: unknown, newPage: number) => {
        setPage(newPage)
    }

    const handleRowsPerPageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setRowsPerPage(parseInt(event.target.value, 10))
        setPage(0)
    }

    const handleSortRequest = (event: React.MouseEvent<unknown>, property: keyof IAccessKeyModel) => {
        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc')
        setOrderBy(property)

    }

    const addMutation = useMutation({
        mutationFn: () => addAccessKey(),
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['accKey'] })
        },
    });

    const revokedMutation = useMutation({
        mutationFn: (id: number) => revokedAccessKey(id),
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['accKey'] })
        },
    });

    const sortedAgentData = useMemo(() =>
        accKeys?.accessKeys.sort((a, b) => {
            if (b[orderBy] < a[orderBy]) return order === 'asc' ? 1 : -1;

            if (b[orderBy] > a[orderBy]) return order === 'asc' ? -1 : 1;

            return 0;
        }),
        [order, orderBy, accKeys]
    )

    const handleAdd = () => {
        addMutation.mutate();
    }

    const handleRevoke = (id: number) => {
        revokedMutation.mutate(id);
    }
    return (
        <DashboardContent maxWidth='xl'>
            <Card>
                <CardHeader title="Access Keys" />
                <CardContent>
                    <Box mb={2} display="flex" justifyContent="space-between" alignItems="center">
                        {/* <PermissionGuard hasContent={false} acceptedPermission="ADD_AGENT"> */}
                        {/* <Button variant="contained" sx={{ mb: 2 }} onClick={() => handleOpen(false, null)}>
                            Add
                        </Button> */}
                        <Button
                            //   component={RouterLink}
                            //   href={paths.dashboard.user.new}
                            variant="contained"
                            startIcon={<Iconify icon="mingcute:add-line" />}
                            onClick={handleAdd}
                        >
                            New
                        </Button>
                        {/* </PermissionGuard> */}
                    </Box>
                    <TableContainer component={Paper}>
                        <Table stickyHeader>
                            <EnhancedTableHead
                                order={order}
                                orderBy={orderBy}
                                onRequestSort={handleSortRequest}
                            />
                            <TableBody>
                                {sortedAgentData?.map((a) => (
                                    <TableRow key={a.id}>
                                        <TableCell>{a.id}</TableCell>
                                        <TableCell>{a.apiKey}</TableCell>
                                        <TableCell ><Label variant="soft"
                                            color={
                                                (a.status === 'ACTIVE' && 'success') ||
                                                (a.status === 'REVOKED' && 'error') ||
                                                'default'
                                            }>{a.status}</Label></TableCell>
                                        <TableCell>{new Date(a.expires_at).toLocaleString()}</TableCell>
                                        <TableCell>{a.revoked_at ? new Date(a.revoked_at).toLocaleString() : '...'}</TableCell>
                                        <TableCell>{new Date(a.created_at).toLocaleString()}</TableCell>
                                        <TableCell>
                                            <Stack direction="row" alignItems="center">
                                                <Tooltip title="Revoke" placement="top" arrow>
                                                    <IconButton
                                                        // color={quickEdit.value ? 'inherit' : 'default'}
                                                        onClick={() => handleRevoke(a.id)}
                                                    >
                                                        <Iconify icon="solar:pen-bold" />
                                                    </IconButton>
                                                </Tooltip>

                                                {/* <IconButton color={popover.open ? 'inherit' : 'default'} onClick={popover.onOpen}>
                                                    <Iconify icon="eva:more-vertical-fill" />
                                                </IconButton> */}
                                            </Stack>
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                        <TablePagination
                            page={page}
                            rowsPerPage={rowsPerPage}
                            count={totalPages}
                            onPageChange={handlePageChange}
                            onRowsPerPageChange={handleRowsPerPageChange}
                            rowsPerPageOptions={ROWS_PER_PAGE_OPTIONS}
                            component="div"
                        />
                    </TableContainer>
                </CardContent>
            </Card>
        </DashboardContent>
    )
}