package com.github.tingshi2015.backend.reminder;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdService {

    public String randomId(){
        return UUID.randomUUID().toString();
    }
}
