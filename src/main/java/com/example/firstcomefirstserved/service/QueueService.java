package com.example.firstcomefirstserved.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class QueueService {

    private final RedisService redisService;


    public void enqueue() {
        UUID uuid = UUID.randomUUID();
        long currentTimeMillis = System.currentTimeMillis();
        redisService.enqueue(uuid, currentTimeMillis);
    }


    public long ranking(String uuid) {
        return redisService.ranking(UUID.fromString(uuid));
    }

    public Set<UUID> publish() {
        return redisService.publish();
    }
}
