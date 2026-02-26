package com.cap.OrderProcessingSystem;


public interface PaymentGateway {
    boolean processPayment(double amount);
}