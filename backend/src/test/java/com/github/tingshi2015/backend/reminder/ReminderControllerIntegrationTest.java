package com.github.tingshi2015.backend.reminder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@AutoConfigureMockMvc
class ReminderControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ReminderRepository reminderRepository;

    @Test
    void getAllReminders_shouldReturnEmptyList_whenRepositoryIsEmpty() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/reminders"))
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                                                                []
                                                                """));
    }

    @Test
    @DirtiesContext
    void getAllReminders_shouldReturnListWithOneObject_whenOneObjectWasSavedInRepository() throws Exception {
        //GIVEN
        Reminder reminder = new Reminder("id1", "name1", LocalTime.of(23, 56, 59), LocalDate.of(2029, 12, 30));
        reminderRepository.save(reminder);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/reminders"))
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                                                                [
                                                                {
                                                                "id": "id1",
                                                                "name": "name1",
                                                                "time": "23:56:59",
                                                                "date": "2029-12-30"
                                                                }
                                                                ]
                                                                """));
    }

    @Test
    @DirtiesContext
    void postAReminder_shouldReturnCreatedProduct() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/reminders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {
                        "name": "name2",
                        "time": "02:56:59",
                        "date": "2027-12-30"
                        }
                        """))
                //THEN
                .andExpect((MockMvcResultMatchers.status().isOk()))
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        {
                        "name": "name2",
                        "time": "02:56:59",
                        "date": "2027-12-30"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void deleteAReminderById() throws Exception {
        //GIVEN
        Reminder existingReminder = new Reminder("id1", "name1", LocalTime.of(23, 56, 59), LocalDate.of(2029, 12, 30));
        reminderRepository.save(existingReminder);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/api/reminders/id1"))
                //THEN
                .andExpect((MockMvcResultMatchers.status().isOk()));
    }
}