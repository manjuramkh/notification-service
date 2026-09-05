package com.bank.domain.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentCompletedEvent(
        UUID paymentId,
        UUID customerId,
        BigDecimal amount,
        String currency,
        String paymentMethod,
        Instant completedAt
) {
}