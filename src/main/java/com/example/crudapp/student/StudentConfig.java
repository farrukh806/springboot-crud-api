package com.example.crudapp.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student farrukh = new Student("Farrukh", LocalDate.of(2000, Month.AUGUST, 25),"email@e.com");
            Student test = new Student("Test", LocalDate.of(2000, Month.AUGUST, 25),"test@test.com");
            studentRepository.saveAll(List.of(farrukh, test));
        };
    }
}