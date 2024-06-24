package com.github.tingshi2015.backend.reminder;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReminderRepository extends MongoRepository<Reminder, String> {

}
