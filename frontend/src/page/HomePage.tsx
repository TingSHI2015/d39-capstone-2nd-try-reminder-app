import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export default function HomePage(){
    const [user, setUser] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
            axios.get("/api/auth/me")
                .then(response =>{setUser(response.data)})
                .catch(()=> {navigate("/login")})   //Redirect to "/login"  if not authenticated
                .finally(() => console.log("finally-getUser"))
//    }, []);
    }, [navigate]);

    const logout = () => {
 //       axios.post("/logout")
 //       axios.post("http://localhost:8080/logout")
 //       axios.get("http://localhost:8080/logout")
        axios.get("/logout")
            .then(()=>{navigate("/login")})
            .catch(error => console.error("Logout failed",error))
            .finally(()=>console.log(("finally-logout")))
    }


    return(
        <>
            {
                user ? (
                    <div>
                        <h3>Welcome, {user}</h3>
                        <button onClick={logout}>Logout</button>
                    </div>
                ) : (
                    <p>Loading...</p>
                )
            }


        </>
    )

}