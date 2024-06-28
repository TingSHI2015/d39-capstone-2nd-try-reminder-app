import "./HomePage.css"
import Header from "../components/Header.tsx";
import ReminderGallery from "../components/ReminderGallery.tsx";
import Footer from "../components/Footer.tsx";
import {Reminder} from "../types/Reminder.ts";
import {ReminderDTO} from "../types/ReminderDTO.ts";
import {ChangeEvent, useState} from "react";

type HomePageProps = {
    user: string | null | undefined,
    reminders: Reminder[],
    saveAReminder: (newReminder: Reminder) => void,
    deleteAReminder: (id: string) => void,
    updateAReminder:(id: string, updateAReminder: ReminderDTO) => void,
}

export default function HomePage(props: Readonly<HomePageProps>){

    const [query, setQuery] = useState<string>("")

    const handleSearchChange = (event: ChangeEvent<HTMLInputElement>) => {
        setQuery(event.target.value)
    }

    const filteredReminders = props.reminders.filter(
        reminder =>
            reminder.name.toLowerCase().includes(query.toLowerCase()) ||
            reminder.time.toLowerCase().includes(query.toLowerCase()) ||
            reminder.date.toLowerCase().includes(query.toLowerCase())
    )


    return(
        <div className="homepage">
            <Header user={props.user} query={query} onSearchChange={handleSearchChange}/>

            <div>
                {
                    filteredReminders.length > 0 ?
                        <ReminderGallery
                            reminders={filteredReminders}
                            saveAReminder={props.saveAReminder}
                            deleteAReminder={props.deleteAReminder}
                            updateAReminder={props.updateAReminder}
                        /> :
                        <p>No Reminders found</p>
                }
            </div>

            <Footer/>


        </div>
    )

}