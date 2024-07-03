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
    private final ReminderService reminderService;
    private final NotificationService notificationService;

    @Scheduled(fixedRate = 60000)   //check every 60 seconds
    public void checkReminders(){
        List<Reminder> upcomingReminders = reminderService.getUpcomingReminders();
        upcomingReminders.forEach(notificationService::sendNotification);
    }
}
