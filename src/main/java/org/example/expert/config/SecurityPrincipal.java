package org.example.expert.config;

import lombok.Getter;
import org.example.expert.domain.user.enums.UserRole;

@Getter
public class SecurityPrincipal {

    final Long userId;
    private final String email;
    private final String nickName;
    private final UserRole userRole;

    public SecurityPrincipal(Long userId, String email, String nickName, UserRole userRole) {
        this.userId = userId;
        this.email = email;
        this.nickName = nickName;
        this.userRole = userRole;
    }
}
