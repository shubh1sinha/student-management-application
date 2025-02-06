package com.assignment.management.repository;

import com.assignment.management.model.entity.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentManagementRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    int deleteByNameAndAgeAndContact(String name, int age, String contact);

    Optional<Student> findByUsid(@NotNull(message = "Age cannot be null or blank") String usid);
}
