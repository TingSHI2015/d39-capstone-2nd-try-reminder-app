package com.github.tingshi2015.backend.reminder;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(Reminder reminder){
        System.out.println("Reminder Alert: " + reminder.name() + " at " + reminder.time() + ", " + reminder.date());
    }
}
