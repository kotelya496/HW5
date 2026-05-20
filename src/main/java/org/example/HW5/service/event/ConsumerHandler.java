package org.example.HW5.service.event;

import lombok.extern.slf4j.Slf4j;
import org.example.HW5.service.ServiceMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerHandler {

    private final ServiceMessage service;

    public ConsumerHandler(ServiceMessage service) {
        this.service = service;
    }

    @KafkaListener(topics = "message-topic", containerFactory = "containerFactory")
    public void messageEventHandler(MessageEvent messageEvent){

        log.info("Поймали сообщение: {}", messageEvent.getStatusMessage());

        service.sendEmail(messageEvent);
    }
}
