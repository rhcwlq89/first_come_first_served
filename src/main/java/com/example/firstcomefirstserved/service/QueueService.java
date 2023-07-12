package com.example.firstcomefirstserved.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class QueueService {
    private static long FIRST_ELEMENT = 0;
    private static long SIZE = 10;
    private static final String EVENT_QUEUE = "event_queue";

    private final RedisTemplate redisTemplate;

    public void enqueue() {
        UUID uuid = UUID.randomUUID();
        long currentTimeMillis = System.currentTimeMillis();

        redisTemplate.opsForZSet().add(EVENT_QUEUE, uuid, currentTimeMillis);
    }


    public void dequeue() {
        long start = FIRST_ELEMENT;
        long end = FIRST_ELEMENT + SIZE - 1;

        redisTemplate.opsForZSet().range(EVENT_QUEUE, start, end).forEach(o -> {
            Long rank = redisTemplate.opsForZSet().rank(EVENT_QUEUE, o);
            log.info("dequeue: {}, {}", o, rank);
        });


    }

    public void publish() {
        long start = FIRST_ELEMENT;
        long end = FIRST_ELEMENT + SIZE;

        redisTemplate.opsForZSet().range(EVENT_QUEUE, start, end).forEach(o -> {
            redisTemplate.opsForZSet().remove(EVENT_QUEUE, o);
            log.info("publish: {}", o);
        });
    }
}
