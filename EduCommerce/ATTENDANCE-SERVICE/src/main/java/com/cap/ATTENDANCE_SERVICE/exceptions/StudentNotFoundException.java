package com.cap.ATTENDANCE_SERVICE.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message){
        super(message);
    }
}