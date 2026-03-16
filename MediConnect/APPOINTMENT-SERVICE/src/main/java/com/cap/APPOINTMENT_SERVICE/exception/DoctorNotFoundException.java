package com.cap.APPOINTMENT_SERVICE.exception;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
