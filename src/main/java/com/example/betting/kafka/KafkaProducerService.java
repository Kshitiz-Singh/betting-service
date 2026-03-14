package com.example.betting.kafka;

import com.example.betting.model.EventOutcome;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, EventOutcome> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, EventOutcome> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEventOutcome(EventOutcome outcome) {
        kafkaTemplate.send("event-outcomes", outcome);
    }
}