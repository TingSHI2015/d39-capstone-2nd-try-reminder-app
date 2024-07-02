import {ChangeEvent, FormEvent, useState} from "react";
import {Reminder} from "../types/Reminder.ts";

type AddAReminderProps ={
    saveAReminder: (newReminder: Reminder) => void,
    initialName: string,
    onClose?: () => void
}

export default function AddAReminder(props: Readonly<AddAReminderProps>){

    const [newReminder, setNewReminder] = useState<Reminder>({id: "", name: props.initialName, time: "", date: ""});

    const handleNewReminderSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        props.saveAReminder(newReminder);
        setNewReminder({id: "", name:"", time:"", date:""});
        if(props.onClose){
            props.onClose();
        }
    };

    const onNewReminderChange = (event: ChangeEvent<HTMLInputElement>) =>{
        setNewReminder({...newReminder, [event.target.name]: event.target.value})
    }

    return (
        <form className="form-add-a-reminder" onSubmit={handleNewReminderSubmit}>
            <input type="text" onChange={onNewReminderChange} value={newReminder.name} name={"name"}
                   placeholder={"Name"}/>
            <input type="time" onChange={onNewReminderChange} value={newReminder.time} name={"time"}
                   placeholder={"Time(HH:MM:SS)"}/>
            <input type="date" onChange={onNewReminderChange} value={newReminder.date} name={"date"}
                   placeholder={"Date(YYYY-MM-DD)"}/>
            <button>
                Add
            </button>
            {
                props.onClose && (
                    <button onClick={props.onClose}>
                        Cancel
                    </button>
                )
            }
        </form>
    )
}