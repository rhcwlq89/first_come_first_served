package com.example.firstcomefirstserved.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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


    public Long ranking(String uuid) {
        return redisService.ranking(UUID.fromString(uuid));
    }

    public Set<UUID> publish() {
        Set publish = redisService.publish();
        publish.forEach(uuid -> {
            UUID uuid1 = UUID.fromString(String.valueOf(uuid));
            redisService.dequeue(uuid1);
            redisService.enqueueProcessing(uuid1, System.currentTimeMillis());
        });

        return publish;
    }

    public UUID processing() {
        Set<String> strings = redisService.publishProcessing();
        if (strings.isEmpty()) {
            return null;
        }

        String s = strings.iterator().next();
        UUID uuid = UUID.fromString(s);
        log.info("processing uuid: {}", uuid);
        redisService.dequeueProcessing(uuid);

        return uuid;
    }
}
