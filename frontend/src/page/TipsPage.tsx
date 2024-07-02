import "./TipsPage.css";
import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import axios from "axios";
import {Tip} from "../types/Tip.ts";
import {TipDTO} from "../types/TipDTO.ts";
import TipCard from "../components/TipCard.tsx";
import {Reminder} from "../types/Reminder.ts";

type TipsPageProps = {
    saveAReminder: (newReminder: Reminder) => void,
}

export default function TipsPage(props: Readonly<TipsPageProps>) {
    const [tips, setTips] = useState<Tip[]>([]);
    const [newTip, setNewTip] = useState<Tip>({id: "", content: ""});

    const handleNewTipSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        addATip(newTip);
        setNewTip({id: "", content: ""})
    }

    const onNewTipChange =  (event: ChangeEvent<HTMLInputElement>) => {
        setNewTip({...newTip, [event.target.name]: event.target.value })
    }

    useEffect(() => {
        axios.get("/api/tips")
            .then(response => setTips(response.data))
            .catch(error => console.error("Error getting default tips", error))
    }, []);


    const addATip = (newTip: Tip) => {
        axios.post("/api/tips", newTip)
            .then(response => {setTips([...tips,response.data])})
            .catch(error => console.error("Error adding a tip", error))
            .finally(()=> console.log("addATip_successful"))
    }


    const  deleteATip = (id: string) => {
        axios.delete(`/api/tips/${id}`)
            .then(() =>  setTips(tips.filter(tip => tip.id !== id)))
            .catch(error => console.error("Error deleting a tip", error))
    }

    const updateATip = (id: string, updateTip: TipDTO) => {
        axios.put(`/api/tips/${id}`, updateTip)
            .then(response => {
                setTips(tips.map(tip => (tip.id === id ? response.data : tip)));
            })
            .catch(error => console.error("Error updating a tip", error))
            .finally(() => {console.log("handelUpdatingATip_successful")})
    }


    return(
        <div className="tips-page">
            <div className="default-tips">
                <h3>
                    Lovely Tips:
                </h3>
                {
                    tips.map(tip => (
                        <TipCard
                            key={tip.id}
                            tip={tip}
                            handelSaveAReminder={props.saveAReminder}
                            handleDeleteATip={deleteATip}
                            handleUpdateATip={updateATip}
                        />
                    ))
                }


                <form onSubmit={handleNewTipSubmit}>
                    <input
                        type="text"
                        onChange={onNewTipChange}
                        value={newTip.content}
                        name="content"
                        placeholder="Add new tip"/>
                    <button type="submit">Add New Tip</button>
                </form>


            </div>

        </div>
    )

}