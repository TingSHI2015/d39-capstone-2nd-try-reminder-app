import "./Footer.css"
import {useNavigate} from "react-router-dom";

export default function Footer(){
    const navigate = useNavigate();

    const handleTipsClick = () => {
        navigate("/tips/")
    }

    return(
        <div className="footer">
            <button onClick={handleTipsClick} className="tips-button">
                Tips
            </button>
        </div>

        )


}
