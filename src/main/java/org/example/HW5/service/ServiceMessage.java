package org.example.HW5.service;

import lombok.RequiredArgsConstructor;
import org.example.HW5.entity.EntityMessage;
import org.example.HW5.repository.MessageRepository;
import org.example.HW5.service.event.MessageEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceMessage {

    private final MessageRepository repository;

    @Transactional
    public void createMessageToBase(EntityMessage message){
        repository.save(message);
    }

    public List<EntityMessage> getAllMessages(){
        return repository.findAll();
    }

}
