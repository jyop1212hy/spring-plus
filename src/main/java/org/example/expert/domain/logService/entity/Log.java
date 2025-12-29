package org.example.expert.domain.logService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@NoArgsConstructor
@Getter
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String action;

    @Column(nullable = false)
    private Long todoId;

    @Column(nullable = false)
    private Long requesterId;

    @Column(nullable = false)
    private Long targetUserId;

    @Column(nullable = false)
    private boolean success;

    @Column(length = 1000)
    private String errorMessage;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Log(String action, Long todoId, Long requesterId, Long targetUserId, boolean success, String errorMessage) {
        this.action = action;
        this.todoId = todoId;
        this.requesterId = requesterId;
        this.targetUserId = targetUserId;
        this.success = success;
        this.errorMessage = errorMessage;
        this.createdAt = createdAt;
    }

    public static Log managerRegisterSuccess(Long todoId, Long requesterId, Long targetUserId) {
        return new Log("MANAGER_REGISTER", todoId, requesterId, targetUserId, true, null);
    }

    public static Log managerRegisterFail(Long todoId, Long requesterId, Long targetUserId, String errorMessage) {
        return new Log("MANAGER_REGISTER", todoId, requesterId, targetUserId, false, errorMessage);
    }
}
