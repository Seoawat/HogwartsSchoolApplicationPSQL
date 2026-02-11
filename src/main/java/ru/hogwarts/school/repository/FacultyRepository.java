package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    // Метод для поиска по цвету (регистронезависимо)
    List<Faculty> findByColorIgnoreCase(String color);

    // Метод для поиска по имени ИЛИ цвету
    @Query("SELECT f FROM Faculty f WHERE LOWER(f.name) = LOWER(:query) OR LOWER(f.color) = LOWER(:query)")
    List<Faculty> findByNameOrColorIgnoreCase(@Param("query") String query);
}