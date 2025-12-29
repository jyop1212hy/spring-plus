package org.example.expert.domain.logService.repository;


import org.example.expert.domain.logService.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
