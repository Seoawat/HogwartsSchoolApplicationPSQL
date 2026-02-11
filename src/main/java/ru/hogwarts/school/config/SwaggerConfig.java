// ============================================================================
// ФАЙЛ: SwaggerConfig.java
// РАСПОЛОЖЕНИЕ: src/main/java/ru/hogwarts/school/config/SwaggerConfig.java
//
// НАЗНАЧЕНИЕ:
// Этот класс настраивает Swagger (OpenAPI). Он задаёт название, описание
// и версию API, чтобы потом можно было открыть красивую документацию
// по адресу http://localhost:8080/swagger-ui.html
// ============================================================================

package ru.hogwarts.school.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hogwarts School API")
                        .version("1.0")
                        .description("API для волшебной школы Хогвартс"));
    }
}