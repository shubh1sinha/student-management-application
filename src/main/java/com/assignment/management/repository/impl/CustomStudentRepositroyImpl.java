package com.assignment.management.repository.impl;

import com.assignment.management.model.entity.Student;
import com.assignment.management.repository.CustomStudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.Set;

@Repository
public class CustomStudentRepositroyImpl implements CustomStudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public long updateStudentFields(Student student, Set<String> fieldsToBeUpdated) {

        StringBuilder queryBuilder = new StringBuilder("UPDATE Student n SET ");

        // Step 1: Build the update query string and collect fields to update
        boolean hasFieldsToUpdate = appendUpdateFields(queryBuilder, student, fieldsToBeUpdated);

        if (!hasFieldsToUpdate) {
            return 0;
        }
        // Step 2: Finalize the query by adding the condition
        queryBuilder.append(" WHERE n.usid = :usid");

        // Step 3: Prepare and set parameters
        Query query = entityManager.createQuery(queryBuilder.toString());
        setQueryParameters(query, student, fieldsToBeUpdated);

        // Step 4: Execute the query and return the result
       long updatedRows =  query.executeUpdate();

        return updatedRows;
    }

    /**
     * Step 1: Appends the update fields to the query and returns whether there are fields to update.
     */
    private boolean appendUpdateFields(StringBuilder queryBuilder, Student student, Set<String> fieldsToBeUpdated) {
        boolean hasFieldsToUpdate = false;
        for (String fieldName : fieldsToBeUpdated) {
            try {
                Field field = Student.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(student);

                if (value != null) {
                    queryBuilder.append("n.").append(fieldName).append(" = :").append(fieldName).append(", ");
                    hasFieldsToUpdate = true;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Log or handle the error here
                return false;
            }
        }
        // Remove trailing comma and space if there are fields to update
        if (hasFieldsToUpdate) {
            queryBuilder.setLength(queryBuilder.length() - 2); // Remove last ", "
        }
        return hasFieldsToUpdate;
    }

    /**
     * Step 3: Sets the parameters for the query.
     */
    private void setQueryParameters(Query query, Student student, Set<String> fieldsToBeUpdated) {
        for (String fieldName : fieldsToBeUpdated) {
            try {
                Field field = Student.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(student);

                if (value != null) {
                    query.setParameter(fieldName, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Log or handle the error here
            }
        }
        query.setParameter("usid", student.getUsid());
    }
}
