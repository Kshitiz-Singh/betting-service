package com.example.betting.rocketmq;

import com.example.betting.model.BetSettlementMessage;
import org.springframework.stereotype.Service;

@Service
public class RocketMQProducerMock {

    private final RocketMQConsumerMock consumer;

    public RocketMQProducerMock(RocketMQConsumerMock consumer) {
        this.consumer = consumer;
    }

    public void send(BetSettlementMessage message) {

        System.out.println("Sending to RocketMQ topic bet-settlements -> " + message);

        consumer.consume(message);
    }
}