package com.cap.APPOINTMENT_SERVICE.exception;

public class AppointmentNotFoundException extends RuntimeException{
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
