package org.example.expert.domain.search;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.todo.todoSearchCond.TodoSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final TodoRepository todoRepository;

    public Page<TodoSearchResponse> search(SearchRequest searchRequest) {

        PageRequest pageable = PageRequest.of(searchRequest.getSize(),searchRequest.getPage());

        TodoSearchCond cond = new TodoSearchCond(
                searchRequest.getTitleKeyword(),
                searchRequest.getNickname(),
                searchRequest.getStart(),
                searchRequest.getEnd()
        );

        Page<TodoSearchResponse> searched = todoRepository.searchTodos(cond, pageable);
        return searched;
    }
}
