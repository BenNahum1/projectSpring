package com.example.projectSpring.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student Ben = new Student(
                    123456789L,
                    "Ben",
                    "bennahum6@gmail.com",
                    LocalDate.of(1995, Month.APRIL,3)
            );
            Student Shai = new Student(
                    987654321L,
                    "Shai",
                    "Shai3@gmail.com",
                    LocalDate.of(2000, Month.APRIL,27)
            );

            repository.saveAll(List.of(Ben,Shai));
        };
    }
}
