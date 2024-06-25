package com.github.tingshi2015.backend.reminder;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @GetMapping
    public List<Reminder> getAllReminders(){
        return reminderService.getAllReminders();
    }


    @PostMapping
    public Reminder postAReminder(@RequestBody ReminderDTO reminderDTO){
        return reminderService.createAReminder(reminderDTO);
    }

}
