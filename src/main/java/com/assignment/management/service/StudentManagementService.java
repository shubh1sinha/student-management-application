package com.assignment.management.service;

import com.assignment.management.model.dto.StudentDTO;
import com.assignment.management.model.dto.UpdateStudentDTO;

import java.util.List;

public interface StudentManagementService {
    List<StudentDTO> listAllStudents();

    List<StudentDTO> findByName(String name);

    String saveStudent(StudentDTO studentInput);

    String deleteStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(UpdateStudentDTO updateStudentDTO);
}
