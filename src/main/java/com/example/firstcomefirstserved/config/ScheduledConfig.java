package com.example.firstcomefirstserved.config;

import com.example.firstcomefirstserved.service.QueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class ScheduledConfig {
    private final QueueService queueService;

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        queueService.publish();
    }
}
