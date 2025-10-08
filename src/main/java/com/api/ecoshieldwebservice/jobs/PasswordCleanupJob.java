package com.api.ecoshieldwebservice.jobs;

import com.api.ecoshieldwebservice.repositories.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class PasswordCleanupJob {
    @Autowired
    PasswordRepository passwordRepository;

    @Scheduled(cron = "0 0 3 * * *")
    public void purgeExpiredTokens() {
        passwordRepository.deleteAllByExpiresAtBefore(OffsetDateTime.now().minusDays(1));
    }
}