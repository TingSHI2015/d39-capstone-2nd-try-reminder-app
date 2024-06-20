import "./ReminderCard.css"
import {ReminderDTO} from "../types/ReminderDTO.ts";

type ReminderCardProps = {
    reminder: ReminderDTO
}

export default function ReminderCard(props: Readonly<ReminderCardProps>){

    return(
        <div className="reminder-card">
            <h3>{props.reminder.name}</h3>
            <p>{props.reminder.time}</p>
            <p>{props.reminder.date}</p>
        </div>
    )
}