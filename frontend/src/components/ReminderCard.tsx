import "./ReminderCard.css"
import {Reminder} from "../types/Reminder.ts";

type ReminderCardProps = {
    reminder: Reminder,
    deleteAReminder: (id: string) => void,
};

export default function ReminderCard(props: Readonly<ReminderCardProps>){

    const handelDelete = () => {
        props.deleteAReminder(props.reminder.id);
    }

    return(
        <div className="reminder-card">
            <h3>{props.reminder.name}</h3>
            <p>{props.reminder.time}</p>
            <p>{props.reminder.date}</p>
            <button onClick={handelDelete}>
                🚮
            </button>
        </div>
    )
}