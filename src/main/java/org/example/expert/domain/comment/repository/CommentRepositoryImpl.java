package org.example.expert.domain.comment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.entity.Comment;
import org.example.expert.domain.comment.entity.QComment;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.QUser;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private BooleanExpression commentContains(Long todoId) {
        return todoId != null ? QComment.comment.todo.id.eq(todoId) : null;
    }

    @Override
    public List<Comment> findByTodoIdWithUser(Long todoId) {

        QComment comment = QComment.comment;
        QUser user = QUser.user;

        return jpaQueryFactory
                .selectFrom(comment)
                .join(comment.user, user).fetchJoin()
                .where(commentContains(todoId))
                .fetch();
    }
}
