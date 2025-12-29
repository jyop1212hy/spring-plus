package org.example.expert.domain.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.expert.domain.common.entity.Timestamped;
import org.example.expert.domain.todo.weatherEnum.Weather;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "todos")
public class Todo extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;

    @Enumerated(EnumType.STRING)
    private Weather weather;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Todo(String title, String contents, Weather weather, Long userId) {
        this.title = title;
        this.contents = contents;
        this.weather = weather;
        this.userId = userId;
    }
}
