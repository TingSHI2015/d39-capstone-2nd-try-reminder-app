import {useEffect, useState} from "react";
import {Reminder} from "../types/Reminder.ts";
import axios from "axios";

export default function UpcomingReminderGallery() {
    const [upcomingReminders, setUpcomingReminders] = useState<Reminder[]>([])

    useEffect(() => {
        getUpcomingReminders()

        const interval = setInterval(() => {getUpcomingReminders();
        }, 60000);

        return () => clearInterval(interval);

    }, []);


    const getUpcomingReminders = () => {
        axios.get("api/reminders/upcoming")
            .then(response => setUpcomingReminders(response.data))
            .catch(error => console.error("Error getting upcoming reminders", error))
            .finally(() => {
                console.log("getUpcomingReminder_successful")
            })
    }

    return(
        <div>
            <h3>Upcoming Reminders</h3>
            <ul>
                {upcomingReminders.map(reminder => (
                <li key={reminder.id}>
                    {reminder.name} at {reminder.date} {reminder.time}
                </li>
                ))}
            </ul>
        </div>
    )

}
