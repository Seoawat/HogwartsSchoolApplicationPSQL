// ============================================================================
// ФАЙЛ: Faculty.java
// РАСПОЛОЖЕНИЕ: src/main/java/ru/hogwarts/school/model/Faculty.java
//
// НАЗНАЧЕНИЕ:
// Это класс-модель для сущности "Факультет". Описывает структуру данных:
// id, название и цвет. Также используется для хранения информации в БД.
// ============================================================================

package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Student> students;

    public Faculty() {}

    public Faculty(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
}