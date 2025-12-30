package org.example.expert.domain.logService.appender;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppenderContextBridge implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        DbLogAppender.setApplicationContext(applicationContext);
    }
}
