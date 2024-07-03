package com.github.tingshi2015.backend.tips;

import com.github.tingshi2015.backend.reminder.IdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class TipService {
    private final TipRepository tipRepository;
    private final IdService idService;

    private Tip convertToEntity(TipDTO tipDTO){
        String id = idService.randomId();
        return new Tip(id, tipDTO.content());
    }

    public List<Tip> getAllTips() {
        return tipRepository.findAll();
    }

    public Tip addTips(TipDTO tipDTO) {
        Tip tipToRepo = convertToEntity(tipDTO);
        return tipRepository.save(tipToRepo);
    }

    public void deleteTip(String id) {
        if (!tipRepository.existsById(id)){
            throw new NoSuchElementException(("Tip with id: " + id + " not found. Can't delete!"));
        }
        tipRepository.deleteById(id);
    }

    public Tip updateATip(TipDTO tipDTO, String id) {
        if (!tipRepository.existsById(id)){
            throw new NoSuchElementException(("Tip with id: " + id + " not found. Can't update!"));
        }
        Tip tipToUpdate = new Tip(id, tipDTO.content());
        return tipRepository.save(tipToUpdate);
    }
}
