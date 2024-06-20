package com.github.tingshi2015.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ReminderDTO> getAllReminders() {
        return reminderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReminderDTO createAReminder(ReminderDTO reminderDTO) {
        Reminder reminderToRepo = convertToEntity(reminderDTO);
        Reminder reminderFromRepo = reminderRepository.save(reminderToRepo);
        return convertToDTO(reminderFromRepo);
    }
}
