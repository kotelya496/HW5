package org.example.HW5.service;

import org.example.HW5.entity.EntityMessage;
import org.example.HW5.service.event.ConsumerHandler;
import org.example.HW5.repository.MessageRepository;
import org.example.HW5.service.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.example.HW5.service.MapperMessage.toEntityMessage;

@Service
public class ServiceMessage {

    private final MessageRepository repository;
    private final JavaMailSender mailSender;

    @Autowired
    public ServiceMessage(MessageRepository repository, JavaMailSender mailSender) {
        this.repository = repository;
        this.mailSender = mailSender;
    }
    @Transactional
    public void createMessageToBase(EntityMessage message){
        repository.save(message);
    }

    public void sendEmail(MessageEvent messageEvent){

        var message = toEntityMessage(messageEvent);
        createMessageToBase(message);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(message.getEmail());
        mailMessage.setSubject("Оповещение от приложения");
        mailMessage.setText(message.getMessage());

        mailSender.send(mailMessage);
    }

}
