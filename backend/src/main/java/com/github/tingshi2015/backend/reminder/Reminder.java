package com.github.tingshi2015.backend.reminder;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "reminders")
public record Reminder(
        @Id
        String id,
        String name,
        //ZonedDateTime dateTime
        LocalTime time,
        LocalDate date
) {
}
