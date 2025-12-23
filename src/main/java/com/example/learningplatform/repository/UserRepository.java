package com.example.learningplatform.repository;

import com.example.learningplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring сам сгенерирует SQL запрос по названию метода!
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}