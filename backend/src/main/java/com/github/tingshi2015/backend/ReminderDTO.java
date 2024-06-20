package com.github.tingshi2015.backend;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReminderDTO(
        String name,
        //ZonedDateTime dateTime
        LocalTime time,
        LocalDate date
) {
}
