import "./HomePage.css"
import Header from "../components/Header.tsx";
import ReminderGallery from "../components/ReminderGallery.tsx";
import Footer from "../components/Footer.tsx";
import {Reminder} from "../types/Reminder.ts";
import {ReminderDTO} from "../types/ReminderDTO.ts";
import {ChangeEvent, useState} from "react";
import AddAReminder from "../components/AddAReminder.tsx";
import UpcomingReminderGallery from "../components/UpcomingReminderGallery.tsx";

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

    console.log(props)

    const filteredReminders = props.reminders.filter(
        reminder =>
            (reminder.name ? reminder.name.toLowerCase().includes(query.toLowerCase()): false) ||
            (reminder.time ? reminder.time.toLowerCase().includes(query.toLowerCase()): false) ||
            (reminder.date ? reminder.date.toString().toLowerCase().includes(query.toLowerCase()): false)
    )


    return(
        <div className="homepage">
            <Header user={props.user} query={query} onSearchChange={handleSearchChange}/>
            <UpcomingReminderGallery />

            <div>
                {
                    filteredReminders.length > 0 ?
                        <ReminderGallery
                            reminders={filteredReminders}
                            // saveAReminder={props.saveAReminder}
                            deleteAReminder={props.deleteAReminder}
                            updateAReminder={props.updateAReminder}
                        /> :
                        <p>No Reminders found</p>
                }

                <AddAReminder saveAReminder={props.saveAReminder} initialName="" />
            </div>

            <Footer/>


        </div>
    )

}