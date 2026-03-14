package com.example.betting.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BetSettlementMessage {

    private Long betId;

    private String userId;

    private String status;

    private double amount;
}