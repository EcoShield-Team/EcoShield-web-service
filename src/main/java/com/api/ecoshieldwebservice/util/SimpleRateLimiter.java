package com.api.ecoshieldwebservice.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimpleRateLimiter {
    private final Map<String, Deque<Long>> requests = new ConcurrentHashMap<>();
    private final int limit = 10;          // 10 req
    private final long windowMs = 60_000; // por 60s

    public boolean allow(String key) {
        long now = Instant.now().toEpochMilli();
        Deque<Long> q = requests.computeIfAbsent(key, k -> new ArrayDeque<>());

        synchronized (q) {
            while (!q.isEmpty() && now - q.peekFirst() > windowMs) {
                q.pollFirst();
            }
            if (q.size() >= limit) return false;
            q.addLast(now);
            return true;
        }
    }
}
