//package com.github.tingshi2015.backend.reminder;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.PrintStream;
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class NotificationServiceTest {
//    private final ByteArrayInputStream outContent = new ByteArrayInputStream();
//    private final PrintStream originalOut = System.out;
//    private NotificationService notificationService;
//
//    @BeforeEach
//    void setUp(){
//        notificationService = new NotificationService();
//        System.setOut(new PrintStream(outContent));
//    }
//
//    @Test
//    void sendNotification() {
//
//        //GIVEN
//        Reminder reminder = new Reminder("id1", "Buy ice cream!", LocalTime.of(14, 30), LocalDate.of(2024, 7, 4));
//
//        //WHEN
//        notificationService.sendNotification(reminder);
//
//        //THEN
//        String expectedOutput = "Reminder Alert: Buy ice cream! at 14:30, 2024-07-04";
//        assertEquals(expectedOutput, notificationService.sendNotification(reminder));
//    }
//}


