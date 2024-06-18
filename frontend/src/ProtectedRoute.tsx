import {Navigate, Outlet} from "react-router-dom";

type ProtectedRouteProps = {
    user: string | null | undefined
}

export default function ProtectedRoute(props: ProtectedRouteProps){
    const isAuthenticated = props.user !== undefined && props.user !== null

    return(
        isAuthenticated ? <Outlet /> : <Navigate to={"/login"} />
    )

}