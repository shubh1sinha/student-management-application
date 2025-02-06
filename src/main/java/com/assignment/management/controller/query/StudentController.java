package com.assignment.management.controller.query;

import com.assignment.management.model.dto.StudentDTO;
import com.assignment.management.service.StudentManagementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query")
@ConditionalOnProperty(name = "app.feature.query.enabled", havingValue = "true")
public class StudentController {

 private final StudentManagementService studentManagementService;

    public StudentController(StudentManagementService studentManagementService) {
        this.studentManagementService = studentManagementService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.ok(studentManagementService.listAllStudents());
    }
    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>> getStudentByName(
            @Valid @RequestParam @NotBlank(message = "Name cannot be blank") String name) {
        // Call service layer to get students by name
        List<StudentDTO> students = studentManagementService.findByName(name);
        if (students.isEmpty()) {
            return ResponseEntity.status(404).body(students); // Return 404 if no students found
        }
        return ResponseEntity.ok(students); // Return 200 OK with the list of students
    }

}
