package com.example.betting.controller;

import com.example.betting.kafka.KafkaProducerService;
import com.example.betting.model.EventOutcome;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

    private final KafkaProducerService producerService;

    public EventController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/outcome")
    public ResponseEntity<String> publishOutcome(@RequestBody EventOutcome outcome) {

        producerService.publishEventOutcome(outcome);

        return ResponseEntity.ok("Event published to Kafka");
    }
}