package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.event.StatusMessage;

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

}