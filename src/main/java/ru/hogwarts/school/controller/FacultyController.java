// ============================================================================
// ФАЙЛ: FacultyController.java
// РАСПОЛОЖЕНИЕ: src/main/java/ru/hogwarts/school/controller/FacultyController.java
//
// НАЗНАЧЕНИЕ:
// Это контроллер для эндпоинтов "/faculty". Работает аналогично StudentController,
// но для сущности "Факультет".
// ============================================================================

package ru.hogwarts.school.controller;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@Tag(name = "Faculty API", description = "Операции с факультетами Хогвартса")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    @Operation(summary = "Создать новый факультет")
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить факультет по ID")
    public ResponseEntity<Faculty> getFaculty(@Parameter(description = "ID факультета") @PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Получить все факультеты")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAllFaculties();
        return ResponseEntity.ok(faculties);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить факультет")
    public ResponseEntity<Faculty> updateFaculty(@Parameter(description = "ID факультета") @PathVariable Long id, @RequestBody Faculty faculty) {
        faculty.setId(id);
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty != null) {
            return ResponseEntity.ok(updatedFaculty);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить факультет")
    public ResponseEntity<Faculty> deleteFaculty(@Parameter(description = "ID факультета") @PathVariable Long id) {
        Faculty deletedFaculty = facultyService.deleteFaculty(id);
        if (deletedFaculty != null) {
            return ResponseEntity.ok(deletedFaculty);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/color/{color}")
    @Operation(summary = "Получить факультеты по цвету")
    public ResponseEntity<List<Faculty>> getFacultiesByColor(@Parameter(description = "Цвет для фильтрации") @PathVariable String color) {
        List<Faculty> faculties = facultyService.getFacultiesByColor(color);
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Faculty>> searchFaculties(
            @RequestParam String query
    ) {
        return ResponseEntity.ok(facultyService.searchByNameOrColor(query));
    }
}