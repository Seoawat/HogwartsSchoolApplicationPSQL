// ============================================================================
// ФАЙЛ: FacultyService.java
// РАСПОЛОЖЕНИЕ: src/main/java/ru/hogwarts/school/service/FacultyService.java
//
// НАЗНАЧЕНИЕ:
// Этот класс содержит бизнес-логику для работы с факультетами.
// Все данные хранятся в PostgreSQL через FacultyRepository.
// ============================================================================

package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public Faculty deleteFaculty(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            facultyRepository.deleteById(id);
            return faculty.get();
        }
        return null;
    }

    public List<Faculty> getFacultiesByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public List<Faculty> searchByNameOrColor(String query) {
        return facultyRepository.findByNameOrColorIgnoreCase(query);
    }
}