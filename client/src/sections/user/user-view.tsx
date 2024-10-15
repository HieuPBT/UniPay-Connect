'use client'

import { useState } from "react";
import { DashboardContent } from "src/layouts/dashboard";

export default function UserView(){
    const [page, setPage] = useState<number>(0);
    const [rowsPerPage, setRowsPerPage] = useState<number>(ROWS_PER_PAGE_OPTIONS[0]);
    const [totalPages, setTotalPages] = useState<number>(0);

    const [orderBy, setOrderBy] = useState<keyof IAccessKeyModel>('id');
    const [order, setOrder] = useState<Order>('asc');

    const queryClient = useQueryClient();

    return(
        <DashboardContent maxWidth='xl'>

        </DashboardContent>
    )
}