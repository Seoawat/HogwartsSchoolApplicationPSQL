// ============================================================================
// ФАЙЛ: Student.java
// РАСПОЛОЖЕНИЕ: src/main/java/ru/hogwarts/school/model/Student.java
//
// НАЗНАЧЕНИЕ:
// Это класс-модель для сущности "Студент". Он описывает структуру данных:
// какие поля у студента (id, имя, возраст). Не содержит логики, только данные.
// ============================================================================

package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    @JsonBackReference
    private Faculty faculty;

    public Student() {}

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }
}