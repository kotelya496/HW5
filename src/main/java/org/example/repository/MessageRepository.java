package org.example.repository;

import org.example.entity.EntityMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<EntityMessage, Long> {
}
