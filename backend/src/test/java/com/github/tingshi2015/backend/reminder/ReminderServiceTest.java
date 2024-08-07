package com.github.tingshi2015.backend.reminder;

import com.github.tingshi2015.backend.exception.InvalidReminderException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;


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
    void createAReminder_withValidFields_shouldSaveReminder() {
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
        assertNotNull(actual);
    }

    @Test
    void createAReminder_withAllFieldsNull_shouldThrowInvalidReminderException() {
        //GIVEN
        ReminderDTO newReminder = new ReminderDTO(null, null, null);

        //WHEN
        InvalidReminderException exception = assertThrows(InvalidReminderException.class, () -> reminderService.createAReminder(newReminder));


        //THEN
        verify(reminderRepository, never()).save(any(Reminder.class));
        assertEquals("Reminder must have at least one non-null filed!", exception.getMessage());

    }

    @Test
    void createAReminder_withNullName_shouldSaveReminder() {
        //GIVEN
        ReminderDTO newReminder = new ReminderDTO(null, LocalTime.of(17, 0, 0), LocalDate.now());
        Reminder reminderToSave = new Reminder("id1", null, LocalTime.of(17, 0, 0), LocalDate.now());

        when(idService.randomId()).thenReturn("id1");
        when(reminderRepository.save(any(Reminder.class))).thenReturn(reminderToSave);

        //WHEN
        Reminder actual = reminderService.createAReminder(newReminder);

        //THEN
        verify(idService).randomId();
        verify(reminderRepository).save(reminderToSave);
        assertEquals(reminderToSave, actual);
        assertNotNull(actual);

    }


    @Test
    void deleteAReminder_shouldInvokeRepositoryDeleteById_withValidId() {
        //GIVEN
        String id = "id1";
        when(reminderRepository.existsById(id)).thenReturn(true);
        doNothing().when(reminderRepository).deleteById(id);

        //WHEN
        reminderService.deleteAReminder(id);

        //THEN
        verify(reminderRepository).existsById(id);
        verify(reminderRepository).deleteById(id);
    }

    @Test
    void deleteAReminder_shouldThrowException_whenIdDoesNotExist() {
        //GIVEN
        String id = "nonExistent-Id";
        when(reminderRepository.existsById(id)).thenReturn(false);

        //WHEN
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> reminderService.deleteAReminder(id));

        //THEN
        assertEquals("Reminder with id: " + id + " not found. Can't delete!", exception.getMessage());
        verify(reminderRepository, never()).deleteById(id);
    }

    @Test
    void updateAReminder_withValidId(){
        //GIVEN
        String id = "id2";
        ReminderDTO updateReminder = new ReminderDTO("name2", LocalTime.of(3,45, 0), LocalDate.of(2027, 1, 2));
        Reminder reminderToUpdate = new Reminder("id2", "name2", LocalTime.of(3,45, 0), LocalDate.of(2027, 1, 2));

        when(reminderRepository.existsById(id)).thenReturn(true);
        when(reminderRepository.save(reminderToUpdate)).thenReturn(reminderToUpdate);

        //WHEN
        Reminder actual = reminderService.updateAReminder(updateReminder, id);

        //THEN
        verify(reminderRepository).existsById(id);
        verify(reminderRepository).save(reminderToUpdate);
        assertEquals(reminderToUpdate, actual);
    }

    @Test
    void  updateAReminder_withInvalidId_shouldThrowException(){
        //GIVEN
        String id = "id2";
        ReminderDTO updateReminder = new ReminderDTO("name2", LocalTime.of(3,45, 0), LocalDate.of(2027, 1, 2));

        when(reminderRepository.existsById(id)).thenReturn(false);

        //WHEN
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> reminderService.updateAReminder(updateReminder, id));

        //THEN
        verify(reminderRepository, never()).save(any(Reminder.class));
        assertEquals("Reminder with id: " + id + " not found. Can't update!", exception.getMessage());
    }


    @Test
    void  getUpcomingReminders_whenTimeAndDateAreNotNull(){
        //GIVEN
        Reminder upcomingReminder1 = new Reminder("id1", "Drink Water!", LocalTime.now().withSecond(0).withNano(0), LocalDate.now());
        Reminder upcomingReminder2 = new Reminder("id2", "Call Mama & Papa!",LocalTime.now().withSecond(0).withNano(0), LocalDate.now());
        List<Reminder> upcomingReminders = List.of(upcomingReminder1, upcomingReminder2);

        when(reminderRepository.findAll()).thenReturn(upcomingReminders);

        //WHEN
        List<Reminder> actual = reminderService.getUpcomingReminders();

        //THEN
        verify(reminderRepository).findAll();
        assertEquals(upcomingReminders, actual);
    }

    @Test
    void  getUpcomingReminders_whenTimeOrDateAreNull(){
        //GIVEN
        Reminder reminderWithoutNull = new Reminder("id1", "Buy milk!", LocalTime.now().withSecond(0).withNano(0), LocalDate.now());
        Reminder reminderWithNullDate = new Reminder("id2", "Drink Water!", LocalTime.now().withSecond(0).withNano(0), null);
        Reminder reminderWithNullTime = new Reminder("id3", "Call Mama & Papa!",null, LocalDate.now());
        Reminder reminderWithNullDateAndTime = new Reminder("id4", "Call Mama & Papa!",null, null);
        List<Reminder> reminders = List.of(reminderWithoutNull, reminderWithNullDate, reminderWithNullTime, reminderWithNullDateAndTime);

        when(reminderRepository.findAll()).thenReturn(reminders);

        //WHEN
        List<Reminder> actual = reminderService.getUpcomingReminders();

        //THEN
        verify(reminderRepository).findAll();
        List<Reminder> expected = List.of(reminderWithoutNull);
        assertEquals(expected, actual);
    }

}