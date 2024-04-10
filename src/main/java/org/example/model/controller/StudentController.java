package org.example.model.controller;

import jakarta.validation.Valid;
import org.example.model.DateValidator;
import org.example.model.Student;
import org.example.model.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Метод для получения списка всех студентов
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Метод для получения студента по идентификатору
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Метод для сохранения студента и возвращения списка всех студентов
    // Метод для сохранения студента и возврата обновленного списка всех студентов
    @PostMapping
    public ResponseEntity<Object> saveStudentAndGetAll(@RequestBody @Valid Student student) {
        // Проверка даты рождения
        // Преобразование даты рождения в строку
        String birthDateStr = new SimpleDateFormat("yyyy-MM-dd").format(student.getBirthDate());
        // Проверка даты рождения
        if (!DateValidator.isValidDate(birthDateStr)) {
            return ResponseEntity.badRequest().body("Invalid birth date. Please use valid date format.");
        }

        // Преобразование даты поступления в строку
        String admissionDateStr = new SimpleDateFormat("yyyy-MM-dd").format(student.getAdmissionDate());
        // Проверка даты поступления
        if (!DateValidator.isValidDate(admissionDateStr)) {
            return ResponseEntity.badRequest().body("Invalid admission date. Please use valid date format.");
        }

        // Сохранение студента
        studentService.saveStudent(student);

        // Получение обновленного списка всех студентов
        List<Student> allStudents = studentService.getAllStudents();
        // Отправка письма с приветствием
        try {
            String recipientEmail = student.getEmail();
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            studentService.sendWelcomeEmail(recipientEmail, firstName, lastName); // Использование метода из сервиса
            System.out.println("Email sent successfully.");
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }


        // Возвращение статуса 200 OK с обновленным списком студентов
        return ResponseEntity.ok().body(allStudents);
    }




    // Метод для обновления данных студента
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    // Метод для удаления студента по идентификатору
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}

