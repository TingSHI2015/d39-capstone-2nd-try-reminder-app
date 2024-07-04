import {useEffect, useState} from "react";
import {Reminder} from "../types/Reminder.ts";
import axios from "axios";

export default function UpcomingReminderGallery() {
    const [upcomingReminders, setUpcomingReminders] = useState<Reminder[]>([]);
    const [showUpcomingReminder, setShowUpcomingReminder] = useState<boolean>(false);

    useEffect(() => {

        getUpcomingReminders();

        const interval = setInterval(() => {
            getUpcomingReminders();
        }, 60000);

        return () => clearInterval(interval); //Cleanup the interval on unmount

    }, []);


    const getUpcomingReminders = () => {
        axios.get("/api/reminders/upcoming")
            .then(response => {
                if(response.data.length > 0) {
                    setUpcomingReminders(response.data);
                    setShowUpcomingReminder(true);
                }
            })
            .catch(error => console.error("Error getting upcoming reminders", error))
            .finally(() => {console.log("getUpcomingReminder_successful")})
    }

    const handleClose = () => {
        setShowUpcomingReminder(false);
        setUpcomingReminders([]);
    }

    return(
        <div>
            {showUpcomingReminder && (
                <div>
                    <h3>Upcoming Reminders</h3>
                    <ul>
                        {upcomingReminders.map(reminder => (
                            <li key={reminder.id}>
                                {reminder.name} at {reminder.date} {reminder.time}
                            </li>
                        ))}
                    </ul>
                    <button onClick={handleClose}>Close</button>
                </div>
            )
            }
        </div>

    )

}
