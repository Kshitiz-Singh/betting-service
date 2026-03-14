package com.example.betting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Bet {

    @Id
    @GeneratedValue
    private Long betId;

    private String userId;

    private String eventId;

    private String eventMarketId;

    private String eventWinnerId;

    private double betAmount;

    private String status;
}