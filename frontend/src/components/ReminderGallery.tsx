import "./ReminderGallery.css"
import {ReminderDTO} from "../types/ReminderDTO.ts";
import ReminderCard from "./ReminderCard.tsx";
import {ChangeEvent, FormEvent, useState} from "react";

type ReminderGalleryProps = {
    reminders: ReminderDTO[],
    saveAReminder: (newReminder: ReminderDTO) => void,

}

export default function ReminderGallery(props: Readonly<ReminderGalleryProps>){
    const cards = props.reminders
        .map(reminder => <ReminderCard reminder={reminder} key={reminder.name}/>)

    const [newReminder, setNewReminder] = useState<ReminderDTO>({name: "", time: "", date: ""});

    const handleNewReminderSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        props.saveAReminder(newReminder);
        setNewReminder({name:"", time:"", date:""});
    };

    const onNewReminderChange = (event: ChangeEvent<HTMLInputElement>) =>{
        setNewReminder({...newReminder, [event.target.name]: event.target.value})
    }

    return (
        <div className="reminder-gallery">
            <h3>Your Reminders:</h3>
            {cards}

            <form className="form-add-a-reminder" onSubmit={handleNewReminderSubmit}>
                <input onChange={onNewReminderChange} value={newReminder.name} name={"name"} placeholder={"Name"}/>
                <input onChange={onNewReminderChange} value={newReminder.time} name={"time"}
                       placeholder={"Time(HH:MM:SS)"}/>
                <input onChange={onNewReminderChange} value={newReminder.date} name={"date"}
                       placeholder={"Date(YYYY-MM-DD)"}/>
                <button>
                    Add
                </button>
            </form>
        </div>
    )


}