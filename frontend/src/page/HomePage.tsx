import axios from "axios";
import {useNavigate} from "react-router-dom";

type HomePageProps = {
    user: string | null | undefined
}

export default function HomePage(props: HomePageProps){

    const navigate = useNavigate();

    const logout = () => {
        axios.get("/logout")
            .then(()=>{navigate("/login")})
            .catch(error => console.error("Logout failed",error))
    }


    return(
        <>
            {
                props.user ? (
                    <div>
                        <h3>Welcome, {props.user}</h3>
                        <button onClick={logout}>Logout</button>
                    </div>
                ) : (
                    <p>Loading...</p>
                )
            }


        </>
    )

}