import "./ReminderGallery.css"
import ReminderCard from "./ReminderCard.tsx";
import {ChangeEvent, FormEvent, useState} from "react";
import {Reminder} from "../types/Reminder.ts";
import {ReminderDTO} from "../types/ReminderDTO.ts";

type ReminderGalleryProps = {
    reminders: Reminder[],
    saveAReminder: (newReminder: Reminder) => void,
    deleteAReminder: (id: string) => void,
    updateAReminder:(id: string, updateAReminder: ReminderDTO) => void,

}

export default function ReminderGallery(props: Readonly<ReminderGalleryProps>){
    const cards = props.reminders
        .map(reminder => <ReminderCard reminder={reminder} key={reminder.id} deleteAReminder={props.deleteAReminder} updateAReminder={props.updateAReminder}/>)

    const [newReminder, setNewReminder] = useState<Reminder>({id: "", name: "", time: "", date: ""});

    const handleNewReminderSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        props.saveAReminder(newReminder);
        setNewReminder({id: "", name:"", time:"", date:""});
    };

    const onNewReminderChange = (event: ChangeEvent<HTMLInputElement>) =>{
        setNewReminder({...newReminder, [event.target.name]: event.target.value})
    }

    return (
        <div className="reminder-gallery">
            <h3>Your Reminders:</h3>
            {cards}

            <form className="form-add-a-reminder" onSubmit={handleNewReminderSubmit}>
                <input type="text" onChange={onNewReminderChange} value={newReminder.name} name={"name"} placeholder={"Name"}/>
                <input type="time" onChange={onNewReminderChange} value={newReminder.time} name={"time"}
                       placeholder={"Time(HH:MM:SS)"}/>
                <input type="date" onChange={onNewReminderChange} value={newReminder.date} name={"date"}
                       placeholder={"Date(YYYY-MM-DD)"}/>
                <button>
                    Add
                </button>
            </form>
        </div>
    )


}