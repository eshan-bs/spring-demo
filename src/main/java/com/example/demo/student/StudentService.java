package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service // same as @Component
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository
                .findStudentsByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email already exists.");
        }
        studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId) {
        if(! studentRepository.existsById(studentId)){
            throw new IllegalStateException(
                    "Student with id " + studentId + " does not exist.");
        }

        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Student newStudent, Long studentId) {
//        if(!studentRepository.existsById(studentId)){
//            throw new IllegalStateException("Student does not exist.");
//        }

        studentRepository.findById(studentId)
                .map(student-> {
                    student.setName(newStudent.getName());
                    student.setEmail(newStudent.getEmail());
                    student.setDob(newStudent.getDob());
                    return studentRepository.save(student);
                })
                .orElseGet(()->{
                    newStudent.setId(studentId);
                    return studentRepository.save(newStudent);
                });

    }
}
