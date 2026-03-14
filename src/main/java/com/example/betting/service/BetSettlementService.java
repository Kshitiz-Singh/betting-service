package com.example.betting.service;

import com.example.betting.model.Bet;
import com.example.betting.model.BetSettlementMessage;
import com.example.betting.model.EventOutcome;
import com.example.betting.repository.BetRepository;
import com.example.betting.rocketmq.RocketMQProducerMock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetSettlementService {

    private final BetRepository betRepository;

    private final RocketMQProducerMock rocketProducer;

    public BetSettlementService(BetRepository betRepository,
                                RocketMQProducerMock rocketProducer) {
        this.betRepository = betRepository;
        this.rocketProducer = rocketProducer;
    }
    public void processEventOutcome(EventOutcome outcome) {

        List<Bet> bets = betRepository.findByEventId(outcome.getEventId());

        for (Bet bet : bets) {

            String status = bet.getEventWinnerId()
                    .equals(outcome.getEventWinnerId())
                    ? "WIN" : "LOSE";

            BetSettlementMessage message =
                    new BetSettlementMessage(
                            bet.getBetId(),
                            bet.getUserId(),
                            status,
                            bet.getBetAmount()
                    );

            rocketProducer.send(message);
        }
    }
}
