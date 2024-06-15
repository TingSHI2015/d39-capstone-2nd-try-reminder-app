
export default function LoginPage(){
    const login = () =>{
        const host =
            window.location.host === "localhost:5173" ?
                "http://localhost:8080":
                window.location.origin

        window.open(host + "/oauth2/authorization/github", "_self")
    }

    return(
        <div>
            <button onClick={login}>Login with GitHub</button>
        </div>
    )
}