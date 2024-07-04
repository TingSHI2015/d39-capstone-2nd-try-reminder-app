package com.github.tingshi2015.backend.reminder;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.*;

class ReminderSchedulerUnitTest {
    ReminderService reminderService = mock(ReminderService.class);
    NotificationService notificationService = mock(NotificationService.class);
    ReminderScheduler reminderScheduler = new ReminderScheduler(reminderService, notificationService);


    @Test
    void checkReminders() {
        //GIVEN
        Reminder reminder1 = new Reminder("id1", "Drink Water!", LocalTime.now().withSecond(0).withNano(0), LocalDate.now());
        Reminder reminder2 = new Reminder("id2", "Call Mama & Papa!",LocalTime.now().withSecond(0).withNano(0), LocalDate.now());

        List<Reminder> reminders = List.of(reminder1, reminder2);

        when(reminderService.getUpcomingReminders()).thenReturn(reminders);
        //doNothing().when(notificationService).sendNotification(reminder1);   //---can be omitted!
        //doNothing().when(notificationService).sendNotification(reminder2);   //---can be omitted!

        //WHEN
       reminderScheduler.checkReminders();

        //THEN
        verify(notificationService, times(1)).sendNotification(reminder1);
        verify(notificationService, times(1)).sendNotification(reminder2);


    }

}




