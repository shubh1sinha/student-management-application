package com.assignment.management.controller.mutation;

import com.assignment.management.model.dto.StudentDTO;
import com.assignment.management.model.request.SaveStudentRequest;
import com.assignment.management.model.request.UpdateStudentRequest;
import com.assignment.management.service.StudentManagementService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing student data, including creation, deletion, and updates.
 * This controller is conditionally enabled based on the application properties.
 * @author - Shubh Sinha
 */
@RestController
@RequestMapping("/api")
@ConditionalOnProperty(name = "app.feature.mutation.enabled", havingValue = "true")
public class StudentManagementController {

    private final StudentManagementService studentManagementService;

    public StudentManagementController(StudentManagementService studentManagementService) {
        this.studentManagementService = studentManagementService;
    }

    @PostMapping(value = "/student")
    public ResponseEntity<String> saveStudent(@RequestBody @Valid SaveStudentRequest input) {
        String result = studentManagementService.saveStudent(input.getData());
        return ResponseEntity.ok(result);

    }

    @DeleteMapping("/student")
    public ResponseEntity<String> deleteStudent(@RequestBody @Valid SaveStudentRequest input) {
        String result = studentManagementService.deleteStudent(input.getData());
        return ResponseEntity.ok(result);
    }

    @PutMapping("/student")
    public  ResponseEntity<StudentDTO> updateStudent(@RequestBody @Valid UpdateStudentRequest input){
        return ResponseEntity.ok(studentManagementService.updateStudent(input.getData()));
    }
}
