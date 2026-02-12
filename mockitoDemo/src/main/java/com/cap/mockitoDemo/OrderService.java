package com.cap.mockitoDemo;

public class OrderService {
	private PaymentService paymentService;
	
	public OrderService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	public String placeOrder(double amount) {
		System.out.println("[OrderService] Placing order...");
		
		boolean paymentSuccess = paymentService.processPayment(amount);
		
		if(paymentSuccess) {
			return "ORDER PLACED";
		}
		return "PAYMENT FAILED";
	}
	
	public boolean validateAndPlaceOrder(double amount) {
		if(amount==0) {
			return false;
		}
		
		boolean paymentSuccess=paymentService.processPayment(amount);
		return paymentSuccess;
		
	}
}