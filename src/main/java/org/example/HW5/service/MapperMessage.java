package org.example.HW5.service;

import org.example.HW5.entity.EntityMessage;
import org.example.HW5.service.event.MessageEvent;
import org.example.HW5.service.event.StatusMessage;

public class MapperMessage {
    public static EntityMessage toEntityMessage(MessageEvent messageEvent){
        return new EntityMessage(messageEvent.getEmailUser()
                ,messageEvent.getStatusMessage()
                ,messageEvent.getStatusMessage() == StatusMessage.CREATED_USER ?
                "Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан." : "Здравствуйте! Ваш аккаунт был удалён.");
    }
}
