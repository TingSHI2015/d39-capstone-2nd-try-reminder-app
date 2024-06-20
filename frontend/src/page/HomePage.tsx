import "./HomePage.css"
import Header from "../components/Header.tsx";
import ReminderGallery from "../components/ReminderGallery.tsx";
import {ReminderDTO} from "../types/ReminderDTO.ts";

type HomePageProps = {
    user: string | null | undefined,
    reminders: ReminderDTO[],
}

export default function HomePage(props: Readonly<HomePageProps>){




    return(
        <div className="homepage">
           <Header user={props.user} />
           <ReminderGallery reminders={props.reminders}/>


        </div>
    )

}