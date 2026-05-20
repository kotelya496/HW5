package org.example.HW5.repository;

import org.example.HW5.entity.EntityMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<EntityMessage, Long> {
}
