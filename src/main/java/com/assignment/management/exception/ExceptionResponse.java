package com.assignment.management.exception;

import java.time.LocalDate;

public class ExceptionResponse {


    private String message;
    private LocalDate timeStamp;
    private String details;

    public ExceptionResponse(String message, LocalDate timeStamp, String details) {
        super();
        this.message = message;
        this.timeStamp = timeStamp;
        this.details = details;
    }

    public ExceptionResponse(LocalDate timeStamp, String details) {
        super();
        this.timeStamp = timeStamp;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public String getDetails() {
        return details;
    }

}
