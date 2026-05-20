package org.example.HW5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.HW5.service.event.StatusMessage;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entity_message")
public class EntityMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "email")
    String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_message")
    StatusMessage statusMessage;
    @Column(name = "massage")
    String message;
    @Column(name = "create_entity_message")
    LocalTime createEntityMessage;

    public EntityMessage(String email, StatusMessage statusMessage, String message) {
        this.email = email;
        this.statusMessage = statusMessage;
        this.message = message;
        this.createEntityMessage = LocalTime.now();
    }
}