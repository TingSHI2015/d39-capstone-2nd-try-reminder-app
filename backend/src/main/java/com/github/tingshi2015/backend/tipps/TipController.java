package com.github.tingshi2015.backend.tipps;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tips")
@RequiredArgsConstructor
public class TipController {

    private final TipService tipService;

//    @GetMapping
//    public List<String> getDefaultTips(){
//        return List.of(
//                "Drink water",
//                "Call Papa & Mama",
//                "Give sb. a Big Hug!",
//                "5-minute eye massage",
//                "Tell self: u'r great!",
//                "Read",
//                "Meditate",
//                "Water plants"
//        );
//    }

    @GetMapping
    List<Tip> getAllTips(){
        return tipService.getAllTips();
    }

    @PostMapping
    public Tip addTip(@RequestBody TipDTO tipDTO){
        return tipService.addTips(tipDTO);
    }

    @DeleteMapping("{id}")
    public void deleteTip(@PathVariable String id){
        tipService.deleteTip(id);
    }

    @PutMapping("{id}")
    public Tip putATip(@RequestBody TipDTO tipDTO, @PathVariable String id){
        return tipService.updateATip(tipDTO, id);
    }
}
