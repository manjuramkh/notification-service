package com.bank.infrastructure.kafka.producer;

import com.bank.domain.event.EventEnvelope;
import com.bank.domain.event.PaymentCompletedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentCompleted(PaymentCompletedEvent payload, String correlationId) {

        EventEnvelope<PaymentCompletedEvent> event = new EventEnvelope<>(
                UUID.randomUUID().toString(),
                "PaymentCompleted",
                "1",
                "payment-service",
                payload.completedAt(),
                correlationId,
                payload.paymentId().toString(),
                payload
        );

        kafkaTemplate.send("payment.events", payload.customerId().toString(), event);
    }
}