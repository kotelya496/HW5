package org.example.HW5.controller;

import lombok.RequiredArgsConstructor;
import org.example.HW5.service.NotificationService;
import org.example.HW5.service.event.MessageEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationControllerHTTP {

    private final NotificationService service;

    @PostMapping()
    public ResponseEntity<Void> createNotification(@RequestBody MessageEvent messageEvent){
        service.sendEmail(messageEvent);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
