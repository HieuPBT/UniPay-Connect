'use client'

import { Avatar, Box, Button, Card, CardContent, CardHeader, Link, Paper, Stack, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow, TableSortLabel } from "@mui/material";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import { useMemo, useState } from "react";
import { DashboardContent } from "src/layouts/dashboard";
import { IUserModel } from "./types/user";
import { getAllUser } from "src/api/getAllUsers";
import { RoleBasedGuard } from "src/auth/guard";
import { useAuthContext } from "src/auth/hooks";
import { IconButton } from "yet-another-react-lightbox";
import { Iconify } from "src/components/iconify";
import Image from "next/image";
import { Label } from "src/components/label";

const ROWS_PER_PAGE_OPTIONS = [10, 25, 50]
type Order = 'asc' | 'desc'

interface HeadCell {
    id: keyof IUserModel;
    label: string;
    numberic: boolean;
    sortable: boolean;
}

const headCells: HeadCell[] = [
    { id: 'id', numberic: true, label: 'ID', sortable: true },
    { id: 'username', numberic: true, label: 'Username', sortable: true },
    // { id: 'fullName', numberic: false, label: 'Ful Name', sortable: true },
    // { id: 'email', numberic: false, label: 'Email', sortable: true },
    { id: 'phoneNumber', numberic: false, label: 'Phone Number', sortable: true },
    // { id: 'avatar', numberic: false, label: 'Avatar', sortable: true },
    { id: 'role', numberic: false, label: 'Role', sortable: true },
    { id: 'active', numberic: false, label: 'Active', sortable: true },
    { id: 'createdAt', numberic: false, label: 'Created At', sortable: true },

];

function EnhancedTableHead(props: {
    onRequestSort: (event: React.MouseEvent<unknown>, property: keyof IUserModel) => void;
    order: Order;
    orderBy: string;
}) {
    const { order, orderBy, onRequestSort } = props;
    const createSortHandler = (property: keyof IUserModel) => (event: React.MouseEvent<unknown>) => {
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

export default function UserView() {
    const [page, setPage] = useState<number>(0);
    const [rowsPerPage, setRowsPerPage] = useState<number>(ROWS_PER_PAGE_OPTIONS[0]);
    const [totalPages, setTotalPages] = useState<number>(0);

    const [orderBy, setOrderBy] = useState<keyof IUserModel>('id');
    const [order, setOrder] = useState<Order>('asc');

    const { user } = useAuthContext();

    const queryClient = useQueryClient();

    const { data: users, isLoading, isError } = useQuery({
        queryKey: ['users'],
        queryFn: async () => {
            const param = {
                page,
                size: rowsPerPage
            };
            const res = await getAllUser(param);
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

    const handleSortRequest = (event: React.MouseEvent<unknown>, property: keyof IUserModel) => {
        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc')
        setOrderBy(property)

    }

    const sortedAgentData = useMemo(() =>
        users?.users.sort((a, b) => {
            if (b[orderBy] < a[orderBy]) return order === 'asc' ? 1 : -1;

            if (b[orderBy] > a[orderBy]) return order === 'asc' ? -1 : 1;

            return 0;
        }),
        [order, orderBy, users]
    )

    return (
        <DashboardContent maxWidth='xl'>
            <RoleBasedGuard hasContent acceptRoles={['ADMIN']} currentRole={user?.role}>
                <Card>
                    <CardHeader title="Users" />
                    <CardContent>
                        <Box mb={2} display="flex" justifyContent="space-between" alignItems="center">
                            {/* <PermissionGuard hasContent={false} acceptedPermission="ADD_AGENT"> */}
                            {/* <Button variant="contained" sx={{ mb: 2 }} onClick={() => handleOpen(false, null)}>
                            Add
                        </Button> */}
                            {/* <Button
                                //   component={RouterLink}
                                //   href={paths.dashboard.user.new}
                                variant="contained"
                                startIcon={<Iconify icon="mingcute:add-line" />}
                                onClick={handleAdd}
                            >
                                New
                            </Button> */}
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
                                            <TableCell>
                                                <Stack spacing={2} direction="row" alignItems="center">
                                                    <Avatar alt={a.fullName} src={a.avatar} />

                                                    <Stack sx={{ typography: 'body2', flex: '1 1 auto', alignItems: 'flex-start' }}>
                                                        <Link color="inherit" sx={{ cursor: 'pointer' }}>
                                                            {a.fullName}
                                                        </Link>
                                                        <Box component="span" sx={{ color: 'text.disabled' }}>
                                                            {a.email}
                                                        </Box>
                                                    </Stack>
                                                </Stack>
                                            </TableCell>

                                            {/* <TableCell>{a.username}</TableCell> */}
                                            {/* <TableCell>{a.fullName}</TableCell> */}
                                            {/* <TableCell>{a.email}</TableCell> */}
                                            <TableCell>{a.phoneNumber}</TableCell>
                                            {/* <TableCell sx={{ p: 1, minWidth: 100, minHeight: 100 }}><Image alt={a.username} width={100} height={100} src={a.avatar ? a.avatar : ''} /> </TableCell> */}
                                            <TableCell>{a.role}</TableCell>
                                            <TableCell ><Label variant="soft"
                                                color={
                                                    (a.active ? 'success' : 'error')
                                                }>{a.active ? 'True ' : 'False'}</Label></TableCell>
                                            {/* <TableCell ><Label variant="soft"
                                                color={
                                                    (a.status === 'ACTIVE' && 'success') || 
                                                    (a.status === 'REVOKED' && 'error') ||
                                                    'default'
                                                }>{a.status}</Label></TableCell> */}
                                            {/* <TableCell>{a.updatedAt ? new Date(a.updatedAt).toLocaleString() : '...'}</TableCell> */}
                                            <TableCell>{new Date(a.createdAt).toLocaleString()}</TableCell>

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
            </RoleBasedGuard>
        </DashboardContent>
    )
}