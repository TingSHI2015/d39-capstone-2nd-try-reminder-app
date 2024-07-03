import "./ReminderGallery.css"
import ReminderCard from "./ReminderCard.tsx";
import {Reminder} from "../types/Reminder.ts";
import {ReminderDTO} from "../types/ReminderDTO.ts";

type ReminderGalleryProps = {
    reminders: Reminder[],
    // saveAReminder: (newReminder: Reminder) => void,
    deleteAReminder: (id: string) => void,
    updateAReminder:(id: string, updateAReminder: ReminderDTO) => void,
}

export default function ReminderGallery(props: Readonly<ReminderGalleryProps>){

    const cards = props.reminders
        .map(reminder => <ReminderCard
            reminder={reminder}
            key={reminder.id}
            deleteAReminder={props.deleteAReminder}
            updateAReminder={props.updateAReminder}/>)


    return (
        <div className="reminder-gallery">
            <h3>Your Reminders:</h3>
            {cards}
        </div>
    )


}