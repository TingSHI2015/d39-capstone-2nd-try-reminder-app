import './App.css'
import {Route, Routes} from "react-router-dom";
import HomePage from "./page/HomePage.tsx";
import LoginPage from "./page/LoginPage.tsx";

function App() {

  return (
      <>
          <Routes>
              <Route path="/login" element={<LoginPage/>}/>
              <Route path="/" element={<HomePage/>}/>

          </Routes>
      </>
  )
}

export default App
