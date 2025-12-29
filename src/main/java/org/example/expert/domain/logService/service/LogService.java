package org.example.expert.domain.logService.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.logService.entity.Log;
import org.example.expert.domain.logService.repository.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void write(Log log) {
        logRepository.save(log);
    }
}
