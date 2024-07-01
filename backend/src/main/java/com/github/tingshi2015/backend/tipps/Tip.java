package com.github.tingshi2015.backend.tipps;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tips")
public record Tip(
        @Id
        String id,
        String content) {
}