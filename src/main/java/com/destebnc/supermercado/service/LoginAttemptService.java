package com.destebnc.supermercado.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class LoginAttemptService {
    private static final int MAX_FAILS = 5;
    private static final Duration BLOCK_TIME = Duration.ofMinutes(15);

    private record Attempts(int fails, Instant blockedUntil) {
    }

    private final Map<String, Attempts> attempts = new ConcurrentHashMap<>();

    public boolean isBlocked(String username) {
        Attempts a = attempts.get(username);
        return a != null && a.blockedUntil() != null && Instant.now().isBefore(a.blockedUntil());
    }

    public void loginFailed(String username) {
        attempts.compute(username, (key, a) -> {
            // Si el bloqueo anterior ya terminó, se empieza a contar de cero
            if (a != null && a.blockedUntil() != null && Instant.now().isAfter(a.blockedUntil())) {
                a = null;
            }
            int fails = (a == null ? 0 : a.fails()) + 1;
            Instant until = fails >= MAX_FAILS ? Instant.now().plus(BLOCK_TIME) : null;
            return new Attempts(fails, until);
        });
    }

    public void loginSucceeded(String username) {
        attempts.remove(username);
    }
}