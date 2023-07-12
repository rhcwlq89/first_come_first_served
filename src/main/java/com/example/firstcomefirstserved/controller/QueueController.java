package com.example.firstcomefirstserved.controller;

import com.example.firstcomefirstserved.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QueueController {

    private final QueueService queueService;

    @PostMapping
    public void enqueue() {
        queueService.enqueue();
    }

    @GetMapping
    public void dequeue() {
        queueService.dequeue();
    }

    @PostMapping("/publish")
    public void publish() {
        queueService.publish();
    }
}
