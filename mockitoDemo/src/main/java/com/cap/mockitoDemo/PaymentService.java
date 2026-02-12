package com.cap.mockitoDemo;

public class PaymentService {
	
	public boolean processPayment(double amount) {
		System.out.println("Processing payment of $"+ amount);
		return true;
	}
	
	public String getTransactionId(double amount) {
		return "TAXI" + System.currentTimeMillis();
	}
}