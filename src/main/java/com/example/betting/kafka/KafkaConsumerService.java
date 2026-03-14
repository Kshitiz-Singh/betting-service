package com.example.betting.kafka;

import com.example.betting.model.EventOutcome;
import com.example.betting.service.BetSettlementService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final BetSettlementService settlementService;

    public KafkaConsumerService(BetSettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @KafkaListener(topics = "event-outcomes", groupId = "betting-group")
    public void consume(EventOutcome outcome) {

        settlementService.processEventOutcome(outcome);

    }
}