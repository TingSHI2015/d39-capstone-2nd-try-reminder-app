package com.github.tingshi2015.backend.tipps;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tips")
@RequiredArgsConstructor

public class TipsController{

    @GetMapping
    public List<String> getDefaultTips(){
        return List.of(
                "Drink water",
                "Call Papa & Mama",
                "Give sb. a Big Hug!",
                "5-minute eye massage",
                "Tell self: u'r great!",
                "Read",
                "Meditate",
                "Water plants"
        );
    }
}
