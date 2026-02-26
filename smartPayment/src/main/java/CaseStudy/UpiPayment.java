package CaseStudy;

import org.springframework.stereotype.Component;

@Component
public class UpiPayment implements PaymentGateways {

	@Override
	public void processPayment(double amount) {
		System.out.println("Payment through UPI: "+amount);
		
	}

}
