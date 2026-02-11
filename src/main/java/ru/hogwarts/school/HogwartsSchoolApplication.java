// ============================================================================
// ФАЙЛ: HogwartsSchoolApplication.java
// РАСПОЛОЖЕНИЕ: src/main/java/ru/hogwarts/school/HogwartsSchoolApplication.java
//
// НАЗНАЧЕНИЕ:
// Это основной класс приложения Spring Boot. Аннотация @SpringBootApplication
// говорит Spring, что это стартовая точка. Метод main запускает встроенный
// веб-сервер и приложение становится доступным, например, на http://localhost:8080
// ============================================================================

package ru.hogwarts.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HogwartsSchoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(HogwartsSchoolApplication.class, args);
    }
}