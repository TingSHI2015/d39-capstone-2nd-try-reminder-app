import "./TipCard.css"
import {Tip} from "../types/Tip.ts";
import {TipDTO} from "../types/TipDTO.ts";
import {ChangeEvent, useState} from "react";
import {Reminder} from "../types/Reminder.ts";
import AddAReminder from "./AddAReminder.tsx";

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

    // const handleCloseAddAReminder = () => {
    //     setShowAddAReminder(false)
    // }

    const handleSaveAReminder = (newReminder: Reminder) => {
        props.handelSaveAReminder(newReminder)
        setShowAddAReminder(false)
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
        props.handleDeleteATip(props.tip.id);
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
                        Add to Reminder
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
                                // onClose={handleCloseAddAReminder}
                            />
                        )}

                </div>
            )}

        </div>
    )

}
