package com.api.ecoshieldwebservice.jobs;

import com.api.ecoshieldwebservice.repositories.PasswordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class PasswordCleanupJob {

    @Autowired
    PasswordRepository passwordRepository;

    private static final Logger log = LoggerFactory.getLogger(PasswordCleanupJob.class);

    @Scheduled(cron = "0 0 * * * *")
    public void purgeExpiredTokens() {
        OffsetDateTime now = OffsetDateTime.now();
        long count = passwordRepository.countByExpiresAtBefore(now);
        passwordRepository.deleteAllByExpiresAtBefore(now);

        if (count > 0) {
            log.info("🧹 Limpieza de tokens: {} tokens expirados eliminados.", count);
        }
    }

}