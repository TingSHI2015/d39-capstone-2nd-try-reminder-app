package com.github.tingshi2015.backend.reminder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        if (!reminderRepository.existsById(id)){
            throw new NoSuchElementException("Reminder with id: " + id + " not found. Can't delete!");
        }
        reminderRepository.deleteById(id);
    }

    public Reminder updateAReminder(ReminderDTO updateReminder, String id) {
        if(!reminderRepository.existsById(id)){
            throw new NoSuchElementException("Reminder with id: " + id + " not found. Can't update!");
        }
        Reminder reminderToUpdate = new Reminder(id, updateReminder.name(), updateReminder.time(), updateReminder.date());
        return reminderRepository.save(reminderToUpdate);
    }

//    public Reminder getReminderById(String id) {
//        return reminderRepository.findById(id)
//                .orElseThrow(()-> new NoSuchElementException("Reminder with id: " + id + " not found. Can't getReminderById"));
//    }
}
