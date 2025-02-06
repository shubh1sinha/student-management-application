package com.assignment.management.service.impl;

import com.assignment.management.exception.DataNotUpdatedException;
import com.assignment.management.exception.InvalidInputException;
import com.assignment.management.exception.StudentNotFoundException;
import com.assignment.management.exception.UnexpectedException;
import com.assignment.management.model.dto.StudentDTO;
import com.assignment.management.model.dto.UpdateStudentDTO;
import com.assignment.management.model.entity.Student;
import com.assignment.management.model.mapper.impl.StudentDtoEntityMapper;
import com.assignment.management.repository.CustomStudentRepository;
import com.assignment.management.repository.StudentManagementRepository;
import com.assignment.management.service.StudentManagementService;
import com.assignment.management.utility.Constants;
import com.assignment.management.utility.StudentIdGenerator;
import com.assignment.management.utility.StudentUtility;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of StudentManagementService that handles operations related to student management.
 *  @author - Shubh Sinha
 */
@Service
public class StudentManagementServiceImpl implements StudentManagementService {
    private static final Logger LOGGER = Logger.getLogger(StudentManagementServiceImpl.class.getName());

    private final StudentManagementRepository studentManagementRepository;
    private final StudentDtoEntityMapper entityMapper;
    private final CustomStudentRepository customStudentRepository;

    /**
     * Constructs a new StudentManagementServiceImpl with the specified dependencies.
     *
     * @param studentManagementRepository the repository for student management
     * @param entityMapper the mapper for converting between entities and DTOs
     * @param customStudentRepository the custom repository for student operations
     */
    public StudentManagementServiceImpl(StudentManagementRepository studentManagementRepository, StudentDtoEntityMapper entityMapper, CustomStudentRepository customStudentRepository) {
        this.studentManagementRepository = studentManagementRepository;
        this.entityMapper = entityMapper;
        this.customStudentRepository = customStudentRepository;
    }

    /**
     * Retrieves a list of all students.
     *
     * @return a list of StudentDTO objects
     */
    @Override
    public List<StudentDTO> listAllStudents() {
        try {
            List<Student> students = studentManagementRepository.findAll();
            return entityMapper.mapToRightList(students);
        } catch (DataAccessException dae) {
            LOGGER.log(Level.SEVERE, "Database access error occurred", dae);
            throw new StudentNotFoundException(Constants.ERROR_NOT_FOUND);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred", e);
            throw new UnexpectedException(Constants.ERRROR_UNEXPECTED);
        }
    }

    /**
     * Finds students by name.
     *
     * @param name the name of the student
     * @return a list of StudentDTO objects
     */
    @Override
    public List<StudentDTO> findByName(String name) {

            if (name == null || name.trim().isEmpty()) {
                LOGGER.log(Level.WARNING, "Provided name is null or empty");
                return Collections.emptyList();
            }

            try {
                List<Student> students = studentManagementRepository.findByName(name);
                return entityMapper.mapToRightList(students);
            } catch (DataAccessException dae) {
                LOGGER.log(Level.SEVERE, String.format(Constants.ERROR_DA_VIOLOATION, name), dae);
                dae.printStackTrace();
                throw new StudentNotFoundException(Constants.ERROR_NOT_FOUND);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE,  String.format(Constants.ERRROR_UNEXPECTED, name), e);
                e.printStackTrace();
                throw new UnexpectedException(Constants.ERRROR_UNEXPECTED);
            }

    }

    /**
     * Saves a student.
     *
     * @param studentInput the student data to save
     * @return a success message
     */
    @Transactional
    @Override
    public String saveStudent(StudentDTO studentInput) {
        try {
            studentInput.setUsid(StudentIdGenerator.generateStudentId(studentInput.getName(), studentInput.getStudentClass()));
            Student student = entityMapper.mapToLeft(studentInput);
            studentManagementRepository.save(student);
            return String.format(Constants.SUCESS_SAVED, student.getName());
        } catch (DataIntegrityViolationException dive) {
            LOGGER.log(Level.WARNING, String.format(Constants.ERROR_DB_INT_VIOLOATION, studentInput.getName()), dive);
            dive.printStackTrace();
            throw new UnexpectedException(Constants.ERRROR_UNEXPECTED);
        } catch (DataAccessException dae) {
            LOGGER.log(Level.SEVERE, String.format(Constants.ERROR_DA_VIOLOATION, studentInput.getName()), dae);
            dae.printStackTrace();
            throw new StudentNotFoundException(Constants.ERROR_NOT_FOUND);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,  String.format(Constants.ERRROR_UNEXPECTED, studentInput.getName()), e);
            e.printStackTrace();
            throw new UnexpectedException(Constants.ERRROR_UNEXPECTED);
        }
    }

    /**
     * Deletes a student.
     *
     * @param studentDTO the student data to delete
     * @return a success message
     */
    @Transactional
    @Override
    public String deleteStudent(StudentDTO studentDTO) {
        if(studentDTO == null){
            LOGGER.log(Level.WARNING, Constants.ERROR_INVALID_DATA);
            throw new InvalidInputException(Constants.ERROR_INVALID_DATA);
        }
        StudentUtility.validateStudentFields(studentDTO);
        try {
            studentManagementRepository.deleteByNameAndAgeAndContact(
                    studentDTO.getName(),
                    studentDTO.getAge(),
                    studentDTO.getContact()
            );
            return "Student deleted successfully!";
        } catch (EmptyResultDataAccessException e) {
            LOGGER.log(Level.WARNING, String.format(Constants.ERROR_NOT_FOUND,studentDTO.getName()) , e);
            e.printStackTrace();
            throw new StudentNotFoundException(Constants.ERROR_NOT_FOUND);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, String.format(Constants.ERRROR_DEL_UNEXPECTED,studentDTO.getName()) , e);
            e.printStackTrace();
            throw new UnexpectedException(Constants.ERRROR_UNEXPECTED);
        }
    }

    /**
     * Updates a student.
     *
     * @param updateStudentDTO the student data to update
     * @return the updated StudentDTO
     */
    @Override
    public StudentDTO updateStudent(UpdateStudentDTO updateStudentDTO) {
        try {
            StudentUtility.validateInput(updateStudentDTO);
            final Student updatedStudent = updatedStudent(updateStudentDTO.getStudent(), updateStudentDTO.getFieldsToBeUpdated());
            return entityMapper.mapToRight(updatedStudent);
        }catch(InvalidInputException | DataNotUpdatedException | StudentNotFoundException ie){
            ie.printStackTrace();
            throw ie;
        }catch (Exception ex){
            ex.printStackTrace();
           throw new UnexpectedException("Unexpected Error while updating");
        }

    }

    /**
     * Updates the fields of a student.
     *
     * @param student the student data
     * @param fieldsToBeUpdated the fields to update
     * @return the updated Student
     */
    @Transactional
    private Student updatedStudent(@NotNull(message = "Student cannot be null or blank") StudentDTO student, @NotBlank(message = "Fields to be updated cannot be blank") Set<String> fieldsToBeUpdated) {
        long count =customStudentRepository.updateStudentFields(entityMapper.mapToLeft(student), fieldsToBeUpdated);
        if(count>0){
            Optional<Student> updatedStudent= studentManagementRepository.findByUsid(student.getUsid());
            return updatedStudent.orElseThrow(
                    ()->new StudentNotFoundException(Constants.ERROR_NOT_FOUND)
            );
        }
        throw  new DataNotUpdatedException(String.format(Constants.ERRROR_DATA_NOT_UDPATED, student.getName()));
    }
}
