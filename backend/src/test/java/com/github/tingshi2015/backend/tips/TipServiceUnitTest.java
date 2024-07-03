package com.github.tingshi2015.backend.tips;

import com.github.tingshi2015.backend.reminder.IdService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TipServiceUnitTest {
    TipRepository tipRepository = mock(TipRepository.class);
    IdService idService = mock(IdService.class);
    TipService tipService = new TipService(tipRepository, idService);


    @Test
    void getAllTips_shouldReturnAllTips() {
        //GIVEN
        Tip tip1 = new Tip("id1", "Drink Water.");
        Tip tip2 = new Tip("id2", "Call Papa & Mama.");
        List<Tip> tips = List.of(tip1, tip2);

        when(tipRepository.findAll()).thenReturn(tips);

        //WHEN
        List<Tip> actual = tipService.getAllTips();

        //THEN
        verify(tipRepository).findAll();
        assertEquals(tips, actual);
    }

    @Test
    void addTips() {
        //GIVEN
        TipDTO tipDTO = new TipDTO("New Tip");
        Tip tipToSave = new Tip("id1", "New Tip");

        when(idService.randomId()).thenReturn("id1");
        when(tipRepository.save(tipToSave)).thenReturn(tipToSave);

        //WHEN
        Tip actual = tipService.addTips(tipDTO);

        //THEN
        verify(idService).randomId();
        verify(tipRepository).save(tipToSave);
        assertEquals(tipToSave, actual);
    }

    @Test
    void deleteTip_withValidId_shouldInvokeRepositoryDeleteById() {
        //GIVEN
        String id = "id1";
        when(tipRepository.existsById(id)).thenReturn(true);
        doNothing().when(tipRepository).deleteById(id);

        //WHEN
        tipService.deleteTip(id);

        //THEN
        verify(tipRepository).existsById(id);
        verify(tipRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteTip_withInvalidId_shouldThrowException() {
        //GIVEN
        String id = "id1";
        when(tipRepository.existsById(id)).thenReturn(false);

        //WHEN
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> tipService.deleteTip(id));

        //THEN
        verify(tipRepository, never()).deleteById(id);
        assertEquals("Tip with id: id1 not found. Can't delete!", exception.getMessage());
    }

    @Test
    void updateATip_withValidId_shouldReturnTip() {
        //GIVEN
        String id = "1ab_c";
        TipDTO updateTip = new TipDTO("Drink Water.");
        Tip tipToUpdate = new Tip("1ab_c", "Drink Water.");

        when(tipRepository.existsById(id)).thenReturn(true);
        when(tipRepository.save(tipToUpdate)).thenReturn(tipToUpdate);

        //WHEN
        Tip actual = tipService.updateATip(updateTip, id);

        //THEN
        verify(tipRepository).existsById(id);
        verify(tipRepository).save(tipToUpdate);
        assertEquals(tipToUpdate, actual);
    }

    @Test
    void updateATip_withInvalidId_shouldThrowException() {
        //GIVEN
        String id = "1ab_c";
        TipDTO updateTip = new TipDTO("Drink Water.");

        when(tipRepository.existsById(id)).thenReturn(false);

        //WHEN
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> tipService.updateATip(updateTip, id));

        //THEN
        verify(tipRepository, never()).save(any(Tip.class));
        assertEquals("Tip with id: 1ab_c not found. Can't update!", exception.getMessage());
    }


}