package org.example.expert.domain.logService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.logService.entity.Log;

@Getter
@RequiredArgsConstructor
public class LogRequest {

    private String massage;
    private Log log;

}
