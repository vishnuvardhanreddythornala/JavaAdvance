package CaseStudy2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class PaymentProcessor {

    private final PaymentService paymentService;

    @Autowired
    private TransactionLogger logger;   // Field Injection

    // Constructor Injection + Qualifier (force UPI)
    public PaymentProcessor(@Qualifier("upiPayment") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void makePayment(double amount) {
        paymentService.processPayment(amount);
        logger.log("Payment of ₹" + amount + " completed");
    }
}
