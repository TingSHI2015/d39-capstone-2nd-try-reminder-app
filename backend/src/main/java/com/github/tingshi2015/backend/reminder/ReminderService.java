package com.github.tingshi2015.backend.reminder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final IdService idService;

    private ReminderDTO convertToDTO(Reminder reminder){
        return new ReminderDTO(reminder.name(), reminder.time(),reminder.date());
    }

    private Reminder convertToEntity(ReminderDTO reminderDTO){
        String id = idService.randomId();
        return new Reminder(id, reminderDTO.name(),reminderDTO.time(),reminderDTO.date());
    }

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public Reminder createAReminder(ReminderDTO reminderDTO) {
        Reminder reminderToRepo = convertToEntity(reminderDTO);
        return reminderRepository.save(reminderToRepo);
    }

    public void deleteAReminder(String id) {
        reminderRepository.deleteById(id);
    }
}
