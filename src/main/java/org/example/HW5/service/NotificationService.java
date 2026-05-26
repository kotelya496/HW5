package org.example.HW5.service;

import lombok.RequiredArgsConstructor;
import org.example.HW5.service.event.MessageEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static org.example.HW5.service.MapperMessage.toEntityMessage;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;
    private final ServiceMessage serviceMessage;

    public void sendEmail(MessageEvent messageEvent){

        var message = toEntityMessage(messageEvent);
        serviceMessage.createMessageToBase(message);
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(message.getEmail());
            mailMessage.setSubject("Оповещение от приложения");
            mailMessage.setText(message.getMessage());

            mailSender.send(mailMessage);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
