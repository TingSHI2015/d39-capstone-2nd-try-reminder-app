import "./ReminderCard.css"
import {Reminder} from "../types/Reminder.ts";

type ReminderCardProps = {
    reminder: Reminder
};

export default function ReminderCard(props: Readonly<ReminderCardProps>){

    return(
        <div className="reminder-card">
            <h3>{props.reminder.name}</h3>
            <p>{props.reminder.time}</p>
            <p>{props.reminder.date}</p>
        </div>
    )
}