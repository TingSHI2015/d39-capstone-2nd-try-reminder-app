import './App.css'
import axios from "axios";

function App() {

    const login = () =>{
        const host = window.location.host === "localhost:5173" ?
                                "http://localhost:8080":
                                window.location.origin

        window.open(host + "/oauth2/authorization/github", "_self")
    }

    const getUser = ()=>{
        axios.get("api/auth/me")
            .then(response =>{
                console.log(response.data)
            })

    }

  return (
      <>
          <button onClick={login}>Login</button>
          <button onClick={getUser}>Me</button>

      </>

  )
}

export default App
