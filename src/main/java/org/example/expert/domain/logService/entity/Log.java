package org.example.expert.domain.logService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
@NoArgsConstructor
@Getter
public class Log {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action;   // MANAGER_REGISTER

    @Column(nullable = false)
    private boolean success;

    @Column(nullable = false)
    private Long todoId;

    @Column(nullable = false)
    private Long requesterId;

    @Column(nullable = false)
    private Long targetUserId;

    @Column(length = 1000)
    private String message;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Log(String action, boolean success, Long todoId, Long requesterId, Long targetUserId, String message) {
        this.action = action;
        this.success = success;
        this.todoId = todoId;
        this.requesterId = requesterId;
        this.targetUserId = targetUserId;
        this.message = message;
    }
}
