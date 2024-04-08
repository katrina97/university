package org.example.model;



import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Метод для сохранения студента
    public Student saveStudent(@Valid Student student) {
        return studentRepository.save(student);
    }

    // Метод для получения списка всех студентов
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Метод для получения студента по идентификатору
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Метод для обновления данных студента
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setBirthDate(updatedStudent.getBirthDate());
        existingStudent.setAdmissionDate(updatedStudent.getAdmissionDate());
        existingStudent.setFaculty(updatedStudent.getFaculty());
        existingStudent.setEmail(updatedStudent.getEmail());


        return studentRepository.save(existingStudent);
    }

    // Метод для удаления студента по идентификатору
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
