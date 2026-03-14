package com.example.betting.rocketmq;

import com.example.betting.model.Bet;
import com.example.betting.model.BetSettlementMessage;
import com.example.betting.repository.BetRepository;
import org.springframework.stereotype.Service;

@Service
public class RocketMQConsumerMock {

    private final BetRepository betRepository;

    public RocketMQConsumerMock(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public void consume(BetSettlementMessage message) {

        Bet bet = betRepository.findById(message.getBetId()).orElseThrow();

        bet.setStatus(message.getStatus());

        betRepository.save(bet);

        System.out.println("Bet settled -> " + message);
    }
}