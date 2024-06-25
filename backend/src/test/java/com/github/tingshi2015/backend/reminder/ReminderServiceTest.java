package com.github.tingshi2015.backend.reminder;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReminderServiceTest {
    ReminderRepository reminderRepository = mock(ReminderRepository.class);
    IdService idService = mock(IdService.class);
    ReminderService reminderService = new ReminderService(reminderRepository, idService);

    @Test
    void getAllReminders() {
        //GIVEN
        Reminder reminder1 = new Reminder("id1","name1", LocalTime.now(), LocalDate.now());
        Reminder reminder2 = new Reminder("id2","name2", LocalTime.of(17, 0, 0), LocalDate.of(2024, 6, 25));
        Reminder reminder3 = new Reminder("id3","name3", LocalTime.of(18, 18, 18), LocalDate.of(2025, 7, 26));
        List<Reminder> reminders = List.of(reminder1, reminder2, reminder3);

        when(reminderRepository.findAll()).thenReturn(reminders);

        //WHEN
        List<Reminder> actual = reminderService.getAllReminders();

        //THEN
        verify(reminderRepository).findAll();
        assertEquals(reminders, actual);
        //assertEquals(List.of(reminder1, reminder2), actual);    //counter-example!
    }

    @Test
    void createAReminder() {
        //GIVEN
        ReminderDTO newReminder = new ReminderDTO("name1", LocalTime.of(17, 0, 0), LocalDate.of(2024, 6, 25));
        Reminder reminderToSave = new Reminder("id1", "name1", LocalTime.of(17, 0, 0), LocalDate.of(2024, 6, 25));

        when(idService.randomId()).thenReturn("id1");
        when(reminderRepository.save(reminderToSave)).thenReturn(reminderToSave);

        //WHEN
        Reminder actual = reminderService.createAReminder(newReminder);

        //THEN
        verify(idService).randomId();
        verify(reminderRepository).save(reminderToSave);
        assertEquals(reminderToSave, actual);

    }


    @Test
    void deleteAReminder_shouldInvokeRepositoryDeleteById() {
        //GIVEN
        String id = "id1";
        doNothing().when(reminderRepository).deleteById(id);

        //WHEN
        reminderService.deleteAReminder(id);

        //THEN
        verify(reminderRepository).deleteById(id);
    }



}