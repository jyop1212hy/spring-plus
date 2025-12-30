package org.example.expert.domain.logService.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.example.expert.domain.logService.entity.Log;
import org.example.expert.domain.logService.repository.LogRepository;
import org.springframework.context.ApplicationContext;

public class DbLogAppender extends AppenderBase<ILoggingEvent> {

private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext ctx) {
        applicationContext = ctx;
    }

    @Override
    protected void append(ILoggingEvent event) {

        if (!"MANAGER_AUDIT".equals(event.getLoggerName())) return;
        if (applicationContext == null) return;

        try {
            LogRepository repo = applicationContext.getBean(LogRepository.class);

            String msg = event.getFormattedMessage();
            if (msg == null || !msg.startsWith("MANAGER_REGISTER|")) return;

            String[] parts = msg.split("\\|", 6);
            boolean success = "SUCCESS".equals(parts[1]);
            Long todoId = Long.parseLong(parts[2]);
            Long requesterId = Long.parseLong(parts[3]);
            Long targetUserId = Long.parseLong(parts[4]);
            String message = parts.length == 6 ? parts[5] : "";

            repo.save(new Log("MANAGER_REGISTER", success, todoId, requesterId, targetUserId, message));

        } catch (Exception ignore) {

        }
    }
}


