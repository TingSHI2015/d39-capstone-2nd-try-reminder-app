package com.github.tingshi2015.backend.reminder;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.management.Notification;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {
    private final ReminderRepository reminderRepository;
    private final NotificationService notificationService;

    @Scheduled(fixedRate = 60000)   //check every 60 seconds
    public void checkReminders(){
        List<Reminder> reminders = reminderRepository.findAll();
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now().withSecond(0).withNano(0); //ignore second & Nano-second

        for (Reminder reminder: reminders){
            if (reminder.date().equals(today) && reminder.time().withSecond(0).withNano(0).equals(now)){
                notificationService.sendNotification(reminder);
            }
        }


    }
}
