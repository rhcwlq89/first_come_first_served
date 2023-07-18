package com.example.firstcomefirstserved.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RedisService {
    private static long FIRST_ELEMENT = 0;
    private static long SIZE = 10;
    private static final String EVENT_QUEUE = "event_queue";
    private static final String EVENT_QUEUE_PROCESSING = "event_queue_processing";

    private final RedisTemplate redisTemplate;

    public void enqueue(UUID uuid, long currentTimeMillis) {
        redisTemplate.opsForZSet().add(EVENT_QUEUE, uuid, currentTimeMillis);
    }

    public void dequeue(UUID uuid) {
        redisTemplate.opsForZSet().remove(EVENT_QUEUE, uuid);
    }

    public void enqueueProcessing(UUID uuid, long currentTimeMillis) {
        redisTemplate.opsForZSet().add(EVENT_QUEUE_PROCESSING, uuid, currentTimeMillis);
    }

    public void dequeueProcessing(UUID uuid) {
        redisTemplate.opsForZSet().remove(EVENT_QUEUE_PROCESSING, uuid);
    }

    public Long ranking(UUID uuid){
        return redisTemplate.opsForZSet().rank(EVENT_QUEUE, uuid);
    }

    public Set<String> publish() {
        long start = FIRST_ELEMENT;
        long end = FIRST_ELEMENT + SIZE;

        return redisTemplate.opsForZSet().range(EVENT_QUEUE, start, end);
    }

    public Set<String> publishProcessing() {
        long start = FIRST_ELEMENT;
        long end = FIRST_ELEMENT + 1;

        return redisTemplate.opsForZSet().range(EVENT_QUEUE_PROCESSING, start, end);
    }
}
