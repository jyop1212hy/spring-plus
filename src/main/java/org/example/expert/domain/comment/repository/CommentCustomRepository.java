package org.example.expert.domain.comment.repository;

import org.example.expert.domain.comment.entity.Comment;
import java.util.List;

public interface CommentCustomRepository {

    List<Comment> findByTodoIdWithUser(Long todoId);
}
