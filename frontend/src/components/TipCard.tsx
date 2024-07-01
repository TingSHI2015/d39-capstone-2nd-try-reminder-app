import "./TipCard.css"
import {Tip} from "../types/Tip.ts";
import {TipDTO} from "../types/TipDTO.ts";
import {ChangeEvent, useState} from "react";
import {Reminder} from "../types/Reminder.ts";
import AddAReminder from "./AddAReminder.tsx";

type TipCardProps = {
    tip: Tip,
    saveAReminder: (newReminder: Reminder) => void,
    deleteATip: (id: string) => void,
    updateATip: (id: string, updateATip: TipDTO) => void,
};

export default function TipCard(props: Readonly<TipCardProps>){
    const [showAddAReminder, setShowAddAReminder] = useState<boolean>(false);

    const [isEditing, setIsEditing] = useState<boolean>(false);
    const [updateTip, setUpdateTip] = useState<Tip>(props.tip);

    const handleClickAddTipToReminder = () => {
        setShowAddAReminder(true)
    }

    const handleCloseAddAReminder = () => {
        setShowAddAReminder(false)
    }

    const handleSaveAReminder = (newReminder: Reminder) => {
        props.saveAReminder(newReminder)
        setShowAddAReminder(false)
    }

    const handleClickEdit = () => {
        setIsEditing(true)
    }

    const handleCancel = () => {
        setIsEditing(false);
        setUpdateTip(props.tip);
    }

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setUpdateTip({...updateTip, [event.target.name]: event.target.value})
    }

    const handleSave = () => {
        props.updateATip(props.tip.id, updateTip);
        setIsEditing(false);
    }

    const handleDelete = () => {
        props.deleteATip(props.tip.id);
    }

    return(
        <div className="tip-card">
            {isEditing ? (
                <div>
                    <input type="text" name = "content" value = {updateTip.content} onChange={handleChange} />
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
                                onClose={handleCloseAddAReminder}
                            />
                        )}

                </div>
            )}

        </div>
    )

}
