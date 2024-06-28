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

    @DeleteMapping("{id}")
    public void deleteAReminder(@PathVariable String id){
        reminderService.deleteAReminder(id);
    }

    @PutMapping("{id}")
    public Reminder putAReminder(@RequestBody ReminderDTO updateReminder, @PathVariable String id){
        return reminderService.updateAReminder(updateReminder, id);
    }

//    @GetMapping("{id}")
//    public Reminder getReminderById(@PathVariable String id){
//        return reminderService.getReminderById(id);
//    }

}
