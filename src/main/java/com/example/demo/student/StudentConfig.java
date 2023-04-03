package com.example.demo.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    private static final Logger log = LoggerFactory.getLogger(StudentConfig.class);

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student mofiz = new Student(
                    "Mofiz",
                    "mofiz@gmail.com",
                    LocalDate.of(2000, JANUARY, 5)
            );

            Student karim= new Student(
                    "Karim",
                    "karim@gmail.com",
                    LocalDate.of(2002, JANUARY, 9)
            );

            log.info("Preloading "+ studentRepository.save(mofiz));
            log.info("Preloading "+ studentRepository.save(karim));

        };
    }


}
