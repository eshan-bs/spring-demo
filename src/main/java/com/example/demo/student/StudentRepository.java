package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Data access layer
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    Optional<Student> findStudentsByEmail(String email);
}
