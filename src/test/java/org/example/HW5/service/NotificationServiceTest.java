package org.example.HW5.service;

import org.example.HW5.entity.EntityMessage;
import org.example.HW5.service.event.MessageEvent;
import org.example.HW5.service.event.StatusMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=true",

        "management.health.mail.enabled=false",

        "spring.mail.host=smtp.test.com",
        "spring.mail.port=587",
        "spring.mail.protocol=smtp",
        "spring.mail.test-connection=false",
})
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ServiceMessage serviceMessage;

    @MockitoBean
    private JavaMailSender mailSender;

    private MessageEvent createdUserEvent;
    private MessageEvent deletedUserEvent;

    @BeforeEach
    void setUp() {
        createdUserEvent = MessageEvent.builder()
                .idUser(1L)
                .emailUser("user@example.com")
                .statusMessage(StatusMessage.CREATED_USER)
                .build();

        deletedUserEvent = MessageEvent.builder()
                .idUser(2L)
                .emailUser("deleted@example.com")
                .statusMessage(StatusMessage.DELETE_USER)
                .build();

        doNothing().when(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendEmail_ShouldSendEmail_WhenUserCreated() {

        notificationService.sendEmail(createdUserEvent);

        verify(mailSender, times(1)).send(argThat((SimpleMailMessage message) -> {
            String[] recipients = message.getTo();
            return recipients != null
                    && recipients.length == 1
                    && "user@example.com".equals(recipients[0])
                    && "Оповещение от приложения".equals(message.getSubject())
                    && "Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан."
                    .equals(message.getText());
        }));
    }

    @Test
    void sendEmail_ShouldCallMailSenderExactlyOnce() {

        notificationService.sendEmail(createdUserEvent);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendEmail_ShouldSaveAndSendForMultipleEvents() {

        notificationService.sendEmail(createdUserEvent);
        notificationService.sendEmail(deletedUserEvent);

        List<EntityMessage> savedMessages = serviceMessage.getAllMessages();
        assertEquals(2, savedMessages.size());

        EntityMessage firstMessage = savedMessages.get(0);
        assertEquals("user@example.com", firstMessage.getEmail());
        assertEquals(StatusMessage.CREATED_USER, firstMessage.getStatusMessage());

        EntityMessage secondMessage = savedMessages.get(1);
        assertEquals("deleted@example.com", secondMessage.getEmail());
        assertEquals(StatusMessage.DELETE_USER, secondMessage.getStatusMessage());

        verify(mailSender, times(2)).send(any(SimpleMailMessage.class));
    }


}
