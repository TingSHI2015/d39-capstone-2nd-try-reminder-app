import "./ReminderCard.css"
import {Reminder} from "../types/Reminder.ts";
import {ReminderDTO} from "../types/ReminderDTO.ts";
import {ChangeEvent, useState} from "react";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

type ReminderCardProps = {
    reminder: Reminder,
    deleteAReminder: (id: string) => void,
    updateAReminder:(id: string, updateAReminder: ReminderDTO) => void,
};

export default function ReminderCard(props: Readonly<ReminderCardProps>){
    const [isEditing, setIsEditing] = useState<boolean>(false);
    const [updateReminder, setUpdateReminder] = useState(props.reminder);
    const MySwal = withReactContent(Swal);     //for React-Sweetalert2

    const handleEdit = () => {
        setIsEditing(true)
    }

    const handleCancel = () => {
        setIsEditing(false);
        setUpdateReminder(props.reminder);
    }

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setUpdateReminder({...updateReminder, [event.target.name]: event.target.value})
    }

    const handleSave = () => {
        props.updateAReminder(props.reminder.id, updateReminder);
        setIsEditing(false);
    }

/*    const handleDelete = () => {
        const confirmed = window.confirm("Are you sure you want to delete this reminder?")
        if (confirmed){
            props.deleteAReminder(props.reminder.id);
        }
    }*/

    const handleDelete = () => {
        MySwal.fire({
            title: 'Are you sure?',
            text: "Do you really want to delete this Reminder?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then(result => {
            if(result.isConfirmed){
                props.deleteAReminder(props.reminder.id);
                MySwal.fire(
                    'Delete!',
                    'Your Reminder has been deleted!',
                    'success'
                )}
        })
    }

    return(
        <div className="reminder-card">
            {isEditing ? (
                <div>
                    <input type="text" name = "name" value = {updateReminder.name} onChange={handleChange} />
                    <input type="time" name = "time" value = {updateReminder.time} onChange={handleChange} />
                    <input type="date" name = "date" value = {updateReminder.date} onChange={handleChange} />
                    <button onClick={handleSave}>Save</button>
                    <button onClick={handleCancel}>Cancel</button>
                </div>
            ) : (
                    <div>
                        <h3>{props.reminder.name}</h3>
                        <p>{props.reminder.time}</p>
                        <p>{props.reminder.date}</p>

                        <button onClick={handleEdit}>
                            Edit
                        </button>

                        <button onClick={handleDelete}>
                            🚮
                        </button>

                    </div>
                )}

        </div>
    )

}