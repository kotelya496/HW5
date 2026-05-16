package org.example.repository;

import org.example.entity.EntityMessager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagerRepository extends JpaRepository<EntityMessager, Long> {
}
