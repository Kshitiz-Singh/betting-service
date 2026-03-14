# Betting Service

A Spring Boot application that processes betting settlements using an event-driven architecture with Apache Kafka and a mock RocketMQ integration.

## How It Works

1. An event outcome is published via the REST API
2. The outcome is sent to a Kafka topic (`event-outcomes`)
3. A Kafka consumer picks up the event and triggers bet settlement
4. The settlement service compares each bet's predicted winner against the actual outcome, marking bets as `WIN` or `LOSE`
5. Settlement messages are forwarded to a mock RocketMQ producer, which updates bet statuses in the database

## Tech Stack

- Java 17
- Spring Boot 3.5
- Apache Kafka (event streaming)
- Spring Data JPA + H2 (in-memory database)
- Lombok
- Docker Compose (Kafka + Zookeeper)

## Project Structure

```
com.example.betting
├── controller/        # REST endpoint to publish event outcomes
├── kafka/             # Kafka producer and consumer
├── model/             # Bet, EventOutcome, BetSettlementMessage
├── repository/        # JPA repository for Bet entity
├── rocketmq/          # Mock RocketMQ producer and consumer
└── service/           # Bet settlement business logic
```

## Prerequisites

- Java 17+
- Maven
- Docker & Docker Compose

## Getting Started

### 1. Start Kafka

```bash
docker-compose up -d
```

### 2. Run the Application

```bash
mvnw spring-boot:run
```

The service starts on `http://localhost:8080`.

### 3. Publish an Event Outcome

```bash
curl -X POST http://localhost:8080/events/outcome \
  -H "Content-Type: application/json" \
  -d '{"eventId": "E1", "eventName": "Match 1", "eventWinnerId": "TEAM_A"}'
```

This settles all bets for event `E1` — bets on `TEAM_A` are marked `WIN`, others `LOSE`.

## Sample Data

The application loads seed data on startup (`data.sql`) with 4 bets on event `E1`:

| Bet ID | User | Predicted Winner | Amount | Status  |
|--------|------|------------------|--------|---------|
| 1      | U1   | TEAM_A           | 100    | PENDING |
| 2      | U2   | TEAM_B           | 200    | PENDING |
| 3      | U3   | TEAM_B           | 200    | PENDING |
| 4      | U4   | TEAM_A           | 50     | PENDING |

## H2 Console

Available at `http://localhost:8080/h2-console` with JDBC URL `jdbc:h2:mem:betdb`.

## Author
Kshitiz Kumar Singh