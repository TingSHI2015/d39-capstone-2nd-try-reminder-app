package com.github.tingshi2015.backend.reminder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final IdService idService;

//    private ReminderDTO convertToDTO(Reminder reminder){
//        return new ReminderDTO(reminder.name(), reminder.time(),reminder.date());
//    }

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

    public List<Reminder> getUpcomingReminders() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now().withSecond(0).withNano(0); //ignore second & Nano-second

        return reminderRepository.findAll().stream()
                .filter(reminder -> reminder.date() != null && reminder.time() != null)
                .filter(reminder -> reminder.date().equals(today) && reminder.time().withSecond(0).withNano(0).equals(now))
                .collect(Collectors.toList());
    }
}
