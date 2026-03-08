package CaseStudy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CreditCardPayment implements PaymentGateways{

	@Override
	public void processPayment(double amount) {
		System.out.println("Payment through CREDIT CARD: "+amount);
		
	}

}
