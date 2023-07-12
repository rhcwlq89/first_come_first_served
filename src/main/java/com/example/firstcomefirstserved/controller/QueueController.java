package com.example.firstcomefirstserved.controller;

import com.example.firstcomefirstserved.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequiredArgsConstructor
@RestController
public class QueueController {

    private final QueueService queueService;

    @PostMapping
    public void enqueue() {
        queueService.enqueue();
    }

    @GetMapping("/{uuid}")
    public Long ranking(@PathVariable String uuid) {
        return queueService.ranking(uuid);
    }

    @PostMapping("/publish")
    public Set publish() {
        return queueService.publish();
    }
}
