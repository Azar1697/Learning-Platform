package com.example.learningplatform;

import com.example.learningplatform.entity.Role;
import com.example.learningplatform.entity.User;
import com.example.learningplatform.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LearningPlatformTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        // Создаем тестового пользователя
        User testUser = new User();
        testUser.setName("Тест Тестович");
        testUser.setEmail("test-" + System.currentTimeMillis() + "@example.com");;
        testUser.setRole(Role.STUDENT);

        // Сохраняем
        User savedUser = userRepository.save(testUser);

        // Проверяем, что он реально в базе
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("Тест Тестович");
    }
}