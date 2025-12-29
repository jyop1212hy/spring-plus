package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.entity.QComment;
import org.example.expert.domain.manager.entity.QManager;
import org.example.expert.domain.search.TodoSearchResponse;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.todoSearchCond.GetTodosCond;
import org.example.expert.domain.todo.todoSearchCond.TodoSearchCond;
import org.example.expert.domain.todo.weatherEnum.Weather;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.example.expert.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private BooleanExpression weatherEq(QTodo todo, Weather weather) {
        return weather != null ? todo.weather.eq(weather) : null;
    }

    private BooleanExpression startGoe(QTodo todo, LocalDateTime startDate) {
        return startDate != null ? todo.modifiedAt.goe(startDate) : null;
    }

    private BooleanExpression endLoe(QTodo todo, LocalDateTime endDate) {
        return endDate != null ? todo.modifiedAt.loe(endDate) : null;
    }


    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {
        QTodo todo = QTodo.todo;
//        QUser user = QUser.user;

        Todo result = jpaQueryFactory
                .selectFrom(todo)
                .leftJoin(user).on(user.id.eq(todo.userId))
                .where(todo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(result);
    }


    @Override
    public Page<TodoResponse> searchTodoResponses(GetTodosCond cond, Pageable pageable) {
        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        List<TodoResponse> content = jpaQueryFactory
                .select(Projections.constructor(TodoResponse.class, todo.id, todo.title, todo.contents, todo.weather.stringValue(),
                        Projections.constructor(UserResponse.class, user.id, user.email),
                        todo.createdAt, todo.modifiedAt
                ))
                .from(todo)
                .leftJoin(user).on(user.id.eq(todo.userId))
                .where(weatherEq(todo, cond.weather()),
                        startGoe(todo, cond.start()),
                        endLoe(todo, cond.end())
                )
                .orderBy(todo.modifiedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(todo.count())
                .from(todo)
                .where(weatherEq(todo, cond.weather()),
                        startGoe(todo, cond.start()),
                        endLoe(todo, cond.end())
                )
                .fetchOne();

        if (total == null) {
            total = 0L;
        }

        return new PageImpl<>(content, pageable, total);
    }


    @Override
    public Page<TodoSearchResponse> searchTodos(TodoSearchCond cond, Pageable pageable) {
        QTodo todo = QTodo.todo;
        QManager manager = QManager.manager;
        QComment comment = QComment.comment;
        QUser user = QUser.user;

        List<TodoSearchResponse> content = jpaQueryFactory
                .select(Projections.constructor(
                        TodoSearchResponse.class,
                        todo.title,
                        JPAExpressions
                                .select(manager.count())
                                .from(manager)
                                .where(manager.todo.eq(todo)),
                        JPAExpressions
                                .select(comment.count())
                                .from(comment)
                                .where(comment.todo.eq(todo))
                ))
                .from(todo)
                .where(
                        titleContains(todo, cond.titleKeyword()),
                        nicknameContains(todo, manager, user, cond.nickname()),
                        createdAtBetween(todo, cond.start(), cond.end())
                )
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(todo.count())
                .from(todo)
                .where(
                        titleContains(todo, cond.titleKeyword()),
                        nicknameContains(todo, manager, user, cond.nickname()),
                        createdAtBetween(todo, cond.start(), cond.end())
                )
                .fetchOne();

        if (total == null) {
            total = 0L;
        }

        return new PageImpl<>(content, pageable, total);
    }


    private BooleanExpression titleContains(QTodo todo, String titleKeyword) {
        return StringUtils.hasText(titleKeyword) ? todo.title.contains(titleKeyword) : null;
    }


    private BooleanExpression nicknameContains(QTodo todo, QManager manager, QUser user, String nickname) {
        if (!StringUtils.hasText(nickname)) {return null;}
        return JPAExpressions
                .selectOne()
                .from(manager)
                .join(manager.user, user)
                .where(manager.todo.eq(todo).and(user.nickname.contains(nickname)))
                .exists();
    }


    private BooleanExpression createdAtBetween(QTodo todo, LocalDateTime start, LocalDateTime end) {
        if(start != null && end != null) {return todo.createdAt.between(start, end);}
        if(start != null) {return todo.createdAt.goe(start);}
        if(end != null) {return todo.createdAt.goe(end);}
        return null;
    }


}
