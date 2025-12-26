package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.weatherEnum.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoCustomRepository {

    Optional<TodoResponse> findTodoResponseById(Long todoId);

    Page<TodoResponse> searchTodoResponses(
            Weather weather,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );

}

