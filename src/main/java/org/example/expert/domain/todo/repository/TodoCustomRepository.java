package org.example.expert.domain.todo.repository;

import org.example.expert.domain.search.TodoSearchResponse;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.todoSearchCond.GetTodosCond;
import org.example.expert.domain.todo.todoSearchCond.TodoSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface TodoCustomRepository {

    Optional<TodoResponse> findTodoResponseById(Long todoId);

    Page<TodoResponse> searchTodoResponses(GetTodosCond cond, Pageable pageable);

    Page<TodoSearchResponse> searchTodos(TodoSearchCond cond, Pageable pageable);

}

