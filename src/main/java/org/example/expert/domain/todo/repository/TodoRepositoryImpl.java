package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.weatherEnum.Weather;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    private BooleanExpression and(BooleanExpression base, BooleanExpression add) {
        return add == null ? base : (base == null ? add : base.and(add));
    }

    @Override
    public Optional<TodoResponse> findTodoResponseById(Long todoId) {
        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        TodoResponse result = jpaQueryFactory
                .select(Projections.constructor(TodoResponse.class, todo.id, todo.title, todo.contents, todo.weather.stringValue(),
                        Projections.constructor(UserResponse.class, user.id, user.email), todo.createdAt, todo.modifiedAt)
                )
                .from(todo)
                .join(user).on(user.id.eq(todo.userId))
                .where(todo.id.eq(todoId))
                .fetchOne();


        return Optional.ofNullable(result);
    }

    @Override
    public Page<TodoResponse> searchTodoResponses(
            Weather weather,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    ) {
        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        BooleanExpression cond = null;
        cond = and(cond, weatherEq(todo, weather));
        cond = and(cond, startGoe(todo, startDate));
        cond = and(cond, endLoe(todo, endDate));

        List<TodoResponse> content = jpaQueryFactory
                .select(Projections.constructor(TodoResponse.class, todo.id, todo.title, todo.contents, todo.weather.stringValue(),
                        Projections.constructor(UserResponse.class, user.id, user.email), todo.createdAt, todo.modifiedAt
                ))
                .from(todo)
                .join(user).on(user.id.eq(todo.userId))
                .where(cond)
                .orderBy(todo.modifiedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(todo.count())
                .from(todo)
                .where(cond)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }


}
