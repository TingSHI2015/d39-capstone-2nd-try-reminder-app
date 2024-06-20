import "./Header.css"
import {useNavigate} from "react-router-dom";
import axios from "axios";

type HeaderProps = {
    user: string | null | undefined
}
export default function Header(props: Readonly<HeaderProps>){
    const navigate = useNavigate();

    const logout = () => {
        axios.get("/logout")
            .then(()=>{navigate("/login")})
            .catch(error => console.error("Logout failed",error))
    }

    return(
        <div>
            {
                props.user ? (
                    <div className="header">
                        <h3>Welcome, {props.user}</h3>
                        <button onClick={logout}>Logout</button>
                    </div>
                ) : (
                    <p>Loading...</p>
                )
            }

        </div>
    )
}