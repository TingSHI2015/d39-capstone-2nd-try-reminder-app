import './App.css'
import {Route, Routes, useNavigate} from "react-router-dom";
import HomePage from "./page/HomePage.tsx";
import LoginPage from "./page/LoginPage.tsx";
import ProtectedRoute from "./ProtectedRoute.tsx";
import {useEffect, useState} from "react";
import axios from "axios";

function App() {
    const [user, setUser] = useState<string | null | undefined>(undefined);
    const navigate = useNavigate();

    useEffect(() => {
        getUser()
    }, []);


    const getUser = () => {
        axios.get("/api/auth/me")
            .then(response =>{
                if(!response.data){
                    navigate("/login");
                }
                else{
                    navigate("/");
                }
                setUser(response.data);
            })
            .catch(()=> {navigate("/login")})   //Redirect to "/login"  if not authenticated
            .finally(() => console.log("finally-getUser"))
    }


  return (
      <>
          <Routes>
              <Route path="/login" element={<LoginPage/>}/>

              <Route element={<ProtectedRoute user={user} />}>
                  <Route path="/" element={<HomePage user={user}/>}/>
              </Route>

          </Routes>
      </>
  )
}

export default App
