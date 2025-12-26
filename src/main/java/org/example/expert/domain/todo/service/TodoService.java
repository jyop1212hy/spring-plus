package org.example.expert.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.client.WeatherClient;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.todo.weatherEnum.Weather;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;
    private final WeatherClient weatherClient;

    public TodoSaveResponse saveTodo(AuthUser authUser, TodoSaveRequest todoSaveRequest) {
        User user = User.fromAuthUser(authUser);

        Weather weather = Weather.fromExternal(weatherClient.getTodayWeather());

        Todo newTodo = new Todo(
                todoSaveRequest.getTitle(),
                todoSaveRequest.getContents(),
                weather,
                user.getId()
        );
        Todo savedTodo = todoRepository.save(newTodo);

        return new TodoSaveResponse(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getContents(),
                weather.name(),
                new UserResponse(user.getId(), user.getEmail())
        );
    }

    public Page<TodoResponse> getTodos(int page, int size, String weather, LocalDateTime start, LocalDateTime end) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Weather weatherEnum = null;
        if (weather != null && !weather.isBlank()) {weatherEnum = Weather.fromExternal(weather);}
        return todoRepository.searchTodoResponses(weatherEnum, start, end, pageable);
    }

    public TodoResponse getTodo(long todoId) {
        return todoRepository.findTodoResponseById(todoId)
                .orElseThrow(() -> new InvalidRequestException("Todo not found"));
    }
}
