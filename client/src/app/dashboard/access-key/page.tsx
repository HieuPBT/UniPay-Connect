import QueryProvider from "src/components/tanstack-query/query-provider";
import AccessKeyView from "src/sections/access-key/access-key-view";

export default function Page() {
    return (
        <QueryProvider>
            <AccessKeyView />
        </QueryProvider>
    )
}