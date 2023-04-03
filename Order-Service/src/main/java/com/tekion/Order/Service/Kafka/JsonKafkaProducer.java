package com.tekion.Order.Service.Kafka;

import Model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JsonKafkaProducer {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void sendMessage(Order order){
        Message<Order> userMessage= MessageBuilder.withPayload(order).setHeader(KafkaHeaders.TOPIC,"orderJson4")
                .build();
        kafkaTemplate.send(userMessage);
    }
}
