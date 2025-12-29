package org.example.expert.domain.todo.todoSearchCond;

import org.example.expert.domain.todo.weatherEnum.Weather;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record GetTodosCond(
        Weather weather,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
) { }
