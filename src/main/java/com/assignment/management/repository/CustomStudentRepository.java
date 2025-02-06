package com.assignment.management.repository;

import com.assignment.management.model.entity.Student;

import java.util.Set;


public interface CustomStudentRepository {
    long updateStudentFields(Student student, Set<String> fieldsToBeUpdated);
}
