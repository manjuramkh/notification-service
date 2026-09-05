package com.bank.infrastructure.kafka.consumer;

import com.bank.domain.event.EventEnvelope;
import com.bank.domain.event.PaymentCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentEventConsumer.class);

    @KafkaListener(
            topics = "payment.events",
            containerFactory = "paymentKafkaListenerContainerFactory"
    )
    public void consume(EventEnvelope<PaymentCompletedEvent> event) {

        log.info(
                "Payment event received. eventId={}, eventType={}, paymentId={}, customerId={}",
                event.eventId(),
                event.eventType(),
                event.payload().paymentId(),
                event.payload().customerId()
        );
    }
}