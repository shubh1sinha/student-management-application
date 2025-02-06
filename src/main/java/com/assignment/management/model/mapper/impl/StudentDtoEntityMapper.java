package com.assignment.management.model.mapper.impl;

import com.assignment.management.model.dto.StudentDTO;
import com.assignment.management.model.entity.Student;
import com.assignment.management.model.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDtoEntityMapper implements BaseMapper<Student, StudentDTO> {
    @Override
    public StudentDTO mapToRight(Student input) {
        final StudentDTO dtoMapping = new StudentDTO();
        dtoMapping.setName(input.getName());
        dtoMapping.setAge(input.getAge());
        dtoMapping.setStudentClass(input.getStudentClass());
        dtoMapping.setContact(input.getContact());
        dtoMapping.setUsid(input.getUsid());
        return dtoMapping;
    }

    @Override
    public Student mapToLeft(StudentDTO input) {
        final Student entityMapping = new Student();
        entityMapping.setName(input.getName());
        entityMapping.setAge(input.getAge());
        entityMapping.setStudentClass(input.getStudentClass());
        entityMapping.setContact(input.getContact());
        entityMapping.setUsid(input.getUsid());
        return entityMapping;
    }

    public List<StudentDTO> mapToRightList(List<Student> students) {
        return students.stream()
                .map(this::mapToRight)
                .toList();
    }
}
