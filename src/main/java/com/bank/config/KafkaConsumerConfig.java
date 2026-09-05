package com.bank.config;


import com.bank.domain.event.EventEnvelope;
import com.bank.domain.event.PaymentCompletedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, EventEnvelope<PaymentCompletedEvent>> paymentEventConsumerFactory() {

        JsonDeserializer<EventEnvelope<PaymentCompletedEvent>> deserializer = new JsonDeserializer<>();

        deserializer.addTrustedPackages("com.example.notification.domain.event");

        Map<String, Object> properties = new HashMap<>();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-service");

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new org.springframework.kafka.core.DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EventEnvelope<PaymentCompletedEvent>> paymentKafkaListenerContainerFactory(ConsumerFactory<String, EventEnvelope<PaymentCompletedEvent>> consumerFactory) {

        var factory = new ConcurrentKafkaListenerContainerFactory<String, EventEnvelope<PaymentCompletedEvent>>();

        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
