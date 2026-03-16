package com.cap.DOCTOR_SERVICE.exceptions;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(String message) {
        super(message);
    }
}