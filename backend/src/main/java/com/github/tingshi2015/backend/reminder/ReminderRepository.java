package com.github.tingshi2015.backend.reminder;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReminderRepository extends MongoRepository<Reminder, String> {

//    @Query("{'date': ?0, 'time': {$gte: ?1, $lt: ?2}}")
//    List<Reminder> findRemindersByDateAndTime(LocalDate date, LocalTime startTime, LocalTime endTime);


}
