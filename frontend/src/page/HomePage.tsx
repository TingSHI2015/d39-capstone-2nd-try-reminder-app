import "./HomePage.css"
import Header from "../components/Header.tsx";
import ReminderGallery from "../components/ReminderGallery.tsx";
import Footer from "../components/Footer.tsx";
import {Reminder} from "../types/Reminder.ts";

type HomePageProps = {
    user: string | null | undefined,
    reminders: Reminder[],
    saveAReminder: (newReminder: Reminder) => void,
}

export default function HomePage(props: Readonly<HomePageProps>){





    return(
        <div className="homepage">
            <Header user={props.user} />
            <ReminderGallery reminders={props.reminders} saveAReminder={props.saveAReminder}/>



           <Footer/>


        </div>
    )

}