package com.assignment.management.utility;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class StudentIdGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int RANDOM_NUMBER_SIZE = 6;

    /**
     * Generates a unique student ID based on the first name, last name, class, and a random number.
     *
     * @param studentClass the class of the student
     * @return a unique student ID
     */
    public static String generateStudentId(String name, int studentClass) {
        String lastName = "";
        String[] nameParts = name.split(" ");
        String firstName = nameParts[0].trim().toUpperCase();
        if(null != nameParts[1].trim().toUpperCase()){
           lastName =  nameParts[1].trim().toUpperCase();
        }
        String randomNumber = generateRandomNumber(RANDOM_NUMBER_SIZE);

        return String.format("%s%s%d%s", firstName, lastName, studentClass, randomNumber);
    }

    /**
     * Generates a random number of the specified size.
     *
     * @param size the size of the random number
     * @return a string representing the random number
     */
    private static String generateRandomNumber(int size) {
        StringBuilder randomNumber = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int digit = RANDOM.nextInt(10); // Generates a random digit between 0 and 9
            randomNumber.append(digit);
        }
        return randomNumber.toString();
    }
}
