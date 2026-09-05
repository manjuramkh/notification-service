package com.bank.domain.event;

import java.time.Instant;

public record EventEnvelope<T>(
        String eventId,
        String eventType,
        String eventVersion,
        String source,
        Instant occurredAt,
        String correlationId,
        String aggregateId,
        T payload
) {
}
