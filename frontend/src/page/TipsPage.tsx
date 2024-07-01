import {useEffect, useState} from "react";
import axios from "axios";
import "./TipsPage.css";

export default function TipsPage() {
    const [defaultTips, setDefaultTips] = useState<string[]>([])

    useEffect(() => {
        axios.get("/api/tips")
            .then(response => setDefaultTips(response.data))
            .catch(error => console.error("Error getting default tips", error))
    }, []);

    return(
        <div className="tips-page">
            <div className="default-tips">
                <h3>
                    Lovely Tips
                </h3>
                {
                    defaultTips.map((tip, index) => (
                        <li key={index}>{tip}</li>
                    ))
                }
            </div>

        </div>
    )

}