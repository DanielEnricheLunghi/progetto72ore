package com.condominio.ConvivoApp.notification.util;

import org.springframework.stereotype.Component;

/**
 * Placeholder for idempotency checks. Implement real store (Redis) later.
 */
@Component
public class IdempotencyUtil {

    public boolean isDuplicate(String key) {
        // TODO: implement redis/cassandra lookup
        return false;
    }
}
