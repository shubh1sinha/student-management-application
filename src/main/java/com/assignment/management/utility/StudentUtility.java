package com.assignment.management.utility;

import com.assignment.management.exception.InvalidInputException;
import com.assignment.management.model.dto.StudentDTO;
import com.assignment.management.model.dto.UpdateStudentDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

/**
 * Utility class for validating student-related data.
 * Provides methods to validate input data for students.
 *
 * Author: Shubh Sinha
 */
@UtilityClass
public class StudentUtility {

    /**
     * Validates the input for updating a student.
     *
     * @param updateStudentDTO the data transfer object containing student update information
     * @throws InvalidInputException if the input is invalid
     */
    public static void validateInput(UpdateStudentDTO updateStudentDTO) {
        if(updateStudentDTO == null){
            throw new InvalidInputException("Invalid Input provided");
        }
        if(updateStudentDTO.getStudent() == null){
            throw new InvalidInputException("Invalid Input provided");
        }
        if(updateStudentDTO.getFieldsToBeUpdated()==null || CollectionUtils.isEmpty(updateStudentDTO.getFieldsToBeUpdated())){
            throw new InvalidInputException("Invalid Input provided for updating student");
        }
        validateStudentFields(updateStudentDTO.getStudent());
    }

    /**
     * Validates the fields of a student.
     *
     * @param student the student data transfer object to validate
     * @throws InvalidInputException if any field is invalid
     */
    public static void validateStudentFields(StudentDTO student) {
            validateField("name", student.getName());
            validateField("age", student.getAge());
            validateField("contact", student.getContact());

    }


    /**
     * Validates an individual field of a student.
     *
     * @param fieldName the name of the field being validated
     * @param fieldValue the value of the field to validate
     * @throws InvalidInputException if the field value is invalid
     */
    private static void validateField(String fieldName, @NotBlank(message = "Object cannot be null or blank") Object fieldValue ) {
        switch (fieldValue) {
            case String s when s.isBlank() -> throw new InvalidInputException(String.format("{%s} field cannot be blank", fieldName));
            case Long l when l <= 0 -> throw new InvalidInputException(String.format("{%s} field cannot be 0 or less than 0", fieldName));
            case null -> throw new InvalidInputException(String.format("{%s} field cannot be null", fieldName));
            default -> {}

        }
    }
}
