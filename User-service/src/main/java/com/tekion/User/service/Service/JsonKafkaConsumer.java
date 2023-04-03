package com.tekion.User.service.Service;

import Model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JsonKafkaConsumer {
    private final UserService userService;
    @KafkaListener(topics = "orderJson4",groupId = "myGroup")
    public void consume(Order message){
        System.out.println(message.toString());
        System.out.println(message.getUserId()+" placed order");
        userService.addUserOrder(message.getUserId(),message);
    }
}
