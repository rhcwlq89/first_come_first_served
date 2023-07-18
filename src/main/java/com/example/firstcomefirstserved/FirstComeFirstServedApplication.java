package com.example.firstcomefirstserved;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FirstComeFirstServedApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstComeFirstServedApplication.class, args);
    }

}
