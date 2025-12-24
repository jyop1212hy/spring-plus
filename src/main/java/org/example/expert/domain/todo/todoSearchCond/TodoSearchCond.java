package org.example.expert.domain.todo.todoSearchCond;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record TodoSearchCond(
        String weather,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
) { }
