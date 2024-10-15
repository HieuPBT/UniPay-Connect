import QueryProvider from "src/components/tanstack-query/query-provider";
import UserView from "src/sections/user/user-view";

export default function Page() {
    return (
        <QueryProvider>
            <UserView />
        </QueryProvider>
    )
}