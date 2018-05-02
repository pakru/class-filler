package com.example.spring.vaadin.repo;

import com.example.spring.vaadin.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
