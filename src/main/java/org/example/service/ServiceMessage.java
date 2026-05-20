package org.example.service;

import org.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceMessage {

    private final MessageRepository repository;

    @Autowired
    public ServiceMessage(MessageRepository repository) {
        this.repository = repository;
    }


}
