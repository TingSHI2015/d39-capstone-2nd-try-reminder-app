import './App.css'
import {Route, Routes, useNavigate} from "react-router-dom";
import HomePage from "./page/HomePage.tsx";
import LoginPage from "./page/LoginPage.tsx";
import ProtectedRoute from "./ProtectedRoute.tsx";
import {useEffect, useState} from "react";
import axios from "axios";
import {Reminder} from "./types/Reminder.ts";
import {ReminderDTO} from "./types/ReminderDTO.ts";
import TipsPage from "./page/TipsPage.tsx";

function App() {
    const [user, setUser] = useState<string | null | undefined>(undefined);
    const navigate = useNavigate();
    const [reminders, setReminders] = useState<Reminder[]>([]);

    useEffect(() => {
        getUser()
    }, []);

    useEffect(() => {
        getAllReminders()
    }, []);


    const getUser = () => {
        axios.get("/api/auth/me")
            .then(response =>{
                setUser(response.data);
                if(!response.data){
                    navigate("/login");
                }
                else{
                    navigate("/");
                }
            })
            .catch(error => console.error("Error getUser", error))
    }

    const getAllReminders = () =>{
        axios.get("api/reminders")
            .then(response => setReminders(response.data))
            .catch(error => console.error("Error getting all Reminders", error))
    }

    const saveAReminder = (newReminder: Reminder) => {
        axios.post("api/reminders", newReminder)
            .then(response => {
                setReminders([...reminders, response.data])
            })
            .catch(error => console.error("Error saving a Reminder!", error))
            .finally(() => {
                console.log("saveReminder_successful")
            })
    }

    const deleteAReminder = (id: string) => {
        axios.delete(`api/reminders/${id}`)
            .then(() => {
                setReminders(reminders.filter(reminder => reminder.id !== id));
            })
            .catch(error => console.error("Error deleting a Reminder!", error))
            .finally(()=>{
                console.log("deleteReminder_successful")
            })
    }

    const updateAReminder = (id: string, updateReminder: ReminderDTO) => {
        axios.put(`api/reminders/${id}`, updateReminder)
            .then(response => {
                setReminders(reminders.map(reminder => (reminder.id === id ? response.data : reminder)));
            })
            .catch(error => console.error("Error updating a Reminder", error))
            .finally(() => {console.log("updatingReminder_successful")})
    }


    return (
      <div className="app-container">
          <Routes>
              <Route path="/login" element={<LoginPage/>}/>

              <Route element={<ProtectedRoute user={user} />}>
                  <Route path="/" element={<HomePage user={user} reminders={reminders} saveAReminder={saveAReminder} deleteAReminder={deleteAReminder} updateAReminder={updateAReminder}/>}/>
                  <Route path="/api/tips" element={<TipsPage saveAReminder={saveAReminder}/>} />
              </Route>

          </Routes>
      </div>
  )
}

export default App
