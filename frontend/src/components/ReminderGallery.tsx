import "./ReminderGallery.css"
import {ReminderDTO} from "../types/ReminderDTO.ts";
import ReminderCard from "./ReminderCard.tsx";

type ReminderGalleryProps = {
    reminders: ReminderDTO[]
}

export default function ReminderGallery(props: Readonly<ReminderGalleryProps>){
    const cards = props.reminders
        .map(reminder => <ReminderCard reminder={reminder} key={reminder.name}/>)

    return (
        <div className="reminder-gallery">
            <h3>Your Reminders:</h3>
            {cards}
        </div>
    )


}