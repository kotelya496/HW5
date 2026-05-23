package org.example.HW5.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.HW5.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerHandler {

    private final NotificationService notificationService;

    @KafkaListener(topics = "message-topic", containerFactory = "containerFactory")
    public void messageEventHandler(MessageEvent messageEvent){

        log.info("Поймали сообщение: {}", messageEvent.getStatusMessage());

        notificationService.sendEmail(messageEvent);
    }
}
