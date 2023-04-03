package com.example.Notification.service.Service;

import Model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;

@Service
@RequiredArgsConstructor
public class JsonKafkaConsumer {
    private final EmailSenderService emailSenderService;
    @KafkaListener(topics = "orderJson4",groupId = "myGroup2")
    public void consume(Order message) throws MessagingException {
        System.out.println(message.toString());
        System.out.println(message.getUserId()+" placed order");
        String attachment = "";
        emailSenderService.sendMailWithAttachment("1905060cse@cit.edu.in","gamergen2401@gmail.com",
                "Order placed "+message.getId(),
                message.toString(),"/Users/vkswaminathan/Desktop/82C262FE-51D6-4F22-8A55-C2A9FA861B06.jpeg");
        File file = new File("/Users/vkswaminathan/Downloads/Daily report  - Sheet1.pdf");

    }
}
