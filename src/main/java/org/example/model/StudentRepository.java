package org.example.model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Методы для выполнения операций доступа к базе данных
}

