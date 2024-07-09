import "./LoginPage.css"

export default function LoginPage(){
    const login = () =>{
        const host =
            window.location.host === "localhost:5173" ?
                "http://localhost:8080":
                window.location.origin

        window.open(host + "/oauth2/authorization/github", "_self")
    }

    return(
        <div className="login-page">
            <h1 className="title">Ting's Reminder-App</h1>

            <button className="login-button" onClick={login}>Login with GitHub</button>

        </div>

    )
}