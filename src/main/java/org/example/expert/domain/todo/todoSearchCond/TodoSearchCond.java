package org.example.expert.domain.todo.todoSearchCond;

import java.time.LocalDateTime;

public record TodoSearchCond(
        String titleKeyword,
        String nickname,
        LocalDateTime start,
        LocalDateTime end
) { }
