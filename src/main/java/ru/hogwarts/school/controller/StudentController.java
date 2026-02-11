// ============================================================================
// ФАЙЛ: StudentController.java
// РАСПОЛОЖЕНИЕ: src/main/java/ru/hogwarts/school/controller/StudentController.java
//
// НАЗНАЧЕНИЕ:
// Это контроллер для эндпоинтов "/student". Он принимает HTTP-запросы
// от клиента (Postman, браузер, Swagger) и передаёт их в StudentService.
// Отвечает за общение между внешним миром и логикой приложения.
// ============================================================================

// Объявление пакета, к которому принадлежит класс
package ru.hogwarts.school.controller;

// Импорт модели Student, которая описывает сущность студента
import ru.hogwarts.school.model.Student;

// Импорт сервиса StudentService, который содержит бизнес-логику
import ru.hogwarts.school.service.StudentService;

// Импорт аннотаций Swagger для документирования API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

// Импорт класса ResponseEntity для формирования HTTP-ответов
import org.springframework.http.ResponseEntity;

// Импорт аннотаций Spring для обработки HTTP-запросов
import org.springframework.web.bind.annotation.*;

// Импорт класса List для работы со списками
import java.util.List;

// Аннотация @RestController объявляет класс как контроллер
// и автоматически добавляет @ResponseBody ко всем методам
// (ответы будут возвращаться в теле HTTP-ответа)
@RestController

// Аннотация @RequestMapping задаёт базовый URL-префикс для всех методов в классе
// все запросы к этому контроллеру будут начинаться с "/student"
@RequestMapping("/student")

// Аннотация @Tag позволяет группировать эндпоинты в Swagger UI
// и задаёт название и описание для всей группы
@Tag(name = "Student API", description = "Операции со студентами Хогвартса")
public class StudentController {

    // Поле для хранения ссылки на сервис StudentService
    // через него контроллер будет обращаться к бизнес-логике
    private final StudentService studentService;

    // Конструктор класса, внедряющий зависимость StudentService
    // Spring автоматически передаст экземпляр сервиса при создании контроллера
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Метод для обработки POST-запросов на "/student"
    // используется для создания нового студента
    @PostMapping
    // Аннотация @Operation описывает метод в Swagger UI
    @Operation(summary = "Создать нового студента")
    // Метод возвращает ResponseEntity<Student>, что позволяет управлять HTTP-статусом
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        // Вызов метода сервиса для создания студента
        // в теле запроса (@RequestBody) передаётся JSON, который Spring автоматически
        // десериализует в объект Student
        Student createdStudent = studentService.createStudent(student);
        // Возврат успешного ответа с кодом 200 и созданным студентом в теле
        return ResponseEntity.ok(createdStudent);
    }

    // Метод для обработки GET-запросов на "/student/{id}"
    // используется для получения студента по ID
    @GetMapping("/{id}")
    // Аннотация @Operation описывает метод в Swagger UI
    @Operation(summary = "Получить студента по ID")
    // Метод принимает ID из URL как переменную пути (@PathVariable)
    // и возвращает ResponseEntity<Student>
    public ResponseEntity<Student> getStudent(@Parameter(description = "ID студента") @PathVariable Long id) {
        // Вызов метода сервиса для получения студента по ID
        Student student = studentService.getStudent(id);
        // Проверка, существует ли студент
        if (student != null) {
            // Возврат успешного ответа с кодом 200 и найденным студентом
            return ResponseEntity.ok(student);
        }
        // Возврат ответа с кодом 404 (Not Found), если студент не найден
        return ResponseEntity.notFound().build();
    }

    // Метод для обработки GET-запросов на "/student" (без ID)
    // используется для получения списка всех студентов
    @GetMapping
    // Аннотация @Operation описывает метод в Swagger UI
    @Operation(summary = "Получить всех студентов")
    // Метод возвращает ResponseEntity<List<Student>>
    public ResponseEntity<List<Student>> getAllStudents() {
        // Вызов метода сервиса для получения всех студентов
        List<Student> students = studentService.getAllStudents();
        // Возврат успешного ответа с кодом 200 и списком студентов
        return ResponseEntity.ok(students);
    }

    // Метод для обработки PUT-запросов на "/student/{id}"
    // используется для обновления существующего студента
    @PutMapping("/{id}")
    // Аннотация @Operation описывает метод в Swagger UI
    @Operation(summary = "Обновить студента")
    // Метод принимает ID из URL как переменную пути (@PathVariable)
    // и объект студента из тела запроса (@RequestBody)
    public ResponseEntity<Student> updateStudent(
            @Parameter(description = "ID студента")
            @PathVariable Long id,
            @RequestBody Student student) {
        // Установка ID из URL в объект студента (на случай, если он не был передан в JSON)
        student.setId(id);
        // Вызов метода сервиса для обновления студента
        Student updatedStudent = studentService.updateStudent(student);
        // Проверка, удалось ли обновить студента (если ID существует)
        if (updatedStudent != null) {
            // Возврат успешного ответа с кодом 200 и обновлённым студентом
            return ResponseEntity.ok(updatedStudent);
        }
        // Возврат ответа с кодом 404 (Not Found), если студент не найден
        return ResponseEntity.notFound().build();
    }

    // Метод для обработки DELETE-запросов на "/student/{id}"
    // используется для удаления студента по ID
    @DeleteMapping("/{id}")
    // Аннотация @Operation описывает метод в Swagger UI
    @Operation(summary = "Удалить студента")
    // Метод принимает ID из URL как переменную пути (@PathVariable)
    public ResponseEntity<Student> deleteStudent(@Parameter(description = "ID студента") @PathVariable Long id) {
        // Вызов метода сервиса для удаления студента по ID
        Student deletedStudent = studentService.deleteStudent(id);
        // Проверка, удалось ли удалить студента (если он существовал)
        if (deletedStudent != null) {
            // Возврат успешного ответа с кодом 200 и удалённым студентом
            return ResponseEntity.ok(deletedStudent);
        }
        // Возврат ответа с кодом 404 (Not Found), если студент не найден
        return ResponseEntity.notFound().build();
    }

    // Метод для обработки GET-запросов на "/student/age/{age}"
    // используется для фильтрации студентов по возрасту
    @GetMapping("/age/{age}")
    // Аннотация @Operation описывает метод в Swagger UI
    @Operation(summary = "Получить студентов по возрасту")
    // Метод принимает возраст из URL как переменную пути (@PathVariable)
    public ResponseEntity<List<Student>> getStudentsByAge(@Parameter(description = "Возраст для фильтрации") @PathVariable int age) {
        // Вызов метода сервиса для получения студентов по возрасту
        List<Student> students = studentService.getStudentsByAge(age);
        // Возврат успешного ответа с кодом 200 и списком студентов
        return ResponseEntity.ok(students);
    }

    @GetMapping("/age/between")
    public ResponseEntity<List<Student>> getStudentsByAgeBetween(
            @RequestParam int min,
            @RequestParam int max
    ) {
        return ResponseEntity.ok(studentService.getStudentsByAgeBetween(min, max));
    }
}