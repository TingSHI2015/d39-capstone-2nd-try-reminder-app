import "./TipCard.css"
import {Tip} from "../types/Tip.ts";
import {TipDTO} from "../types/TipDTO.ts";
import {ChangeEvent, useState} from "react";
import {Reminder} from "../types/Reminder.ts";
import AddAReminder from "./AddAReminder.tsx";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

const MySwal = withReactContent(Swal);     //for React-Sweetalert2

type TipCardProps = {
    tip: Tip,
    handelSaveAReminder: (newReminder: Reminder) => void,
    handleDeleteATip: (id: string) => void,
    handleUpdateATip: (id: string, updateATip: TipDTO) => void,
};


export default function TipCard(props: Readonly<TipCardProps>){
    const [showAddAReminder, setShowAddAReminder] = useState<boolean>(false);
    const [isEditing, setIsEditing] = useState<boolean>(false);
    const [updatedTip, setUpdatedTip] = useState<Tip>(props.tip);

    const handleClickAddTipToReminder = () => {
        setShowAddAReminder(true)
    }

    const handleCloseAddAReminder = () => {
        setShowAddAReminder(false)
    }

    const handleSaveAReminder = (newReminder: Reminder) => {
        props.handelSaveAReminder(newReminder)
        setShowAddAReminder(false)
        // alert(`Tip "${props.tip.content}" has been successfully added as a reminder.`)
        MySwal.fire({
            title: 'Success!',
            text: `Tip "${props.tip.content}" has been successfully added as a reminder.`,
            icon: 'success',
            confirmButtonText: 'OK',
            timer: 2500,
            toast: true,
            position: "top-end",
            showConfirmButton: false
            });
    }

    const handleClickEdit = () => {
        setIsEditing(true)
    }

    const handleCancel = () => {
        setIsEditing(false);
        setUpdatedTip(props.tip);
    }

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setUpdatedTip({...updatedTip, [event.target.name]: event.target.value})
    }

    const handleSave = () => {
        props.handleUpdateATip(props.tip.id, updatedTip);
        setIsEditing(false);
    }

    const handleDelete = () => {
        const confirmed = window.confirm("Are you sure you want to delete this Tip?")
        if (confirmed) {
            props.handleDeleteATip(props.tip.id);
        }
    }

    return(
        <div className="tip-card">
            {isEditing ? (
                <div>
                    <input type="text" name = "content" value = {updatedTip.content} onChange={handleChange} />
                    <button onClick={handleSave}>Save</button>
                    <button onClick={handleCancel}>Cancel</button>
                </div>
            ) : (
                <div>
                    <h3>{props.tip.content}</h3>

                    <button onClick={handleClickAddTipToReminder}>
                        Add to my Reminder
                    </button>

                    <button onClick={handleClickEdit}>
                        Edit
                    </button>

                    <button onClick={handleDelete}>
                        🚮
                    </button>

                    {
                        showAddAReminder && (
                            <AddAReminder
                                saveAReminder={handleSaveAReminder}
                                initialName={props.tip.content}
                                onClose={handleCloseAddAReminder}
                            />
                        )}

                </div>
            )}

        </div>
    )

}
