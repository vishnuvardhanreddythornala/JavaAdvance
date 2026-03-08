package CaseStudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    private final PaymentGateways paymentGateways;
    private final ApplicationContext context;

    @Autowired
    private NotificationServices notificationServices;

    @Autowired
    public PaymentService(PaymentGateways paymentGateways,ApplicationContext context) {
        this.paymentGateways = paymentGateways;
        this.context = context;
    }

    public void processPayment(double amount) {

        paymentGateways.processPayment(amount);
        notificationServices.sendMessage();

        ReceiptGenerator receipt = context.getBean(ReceiptGenerator.class); 

        receipt.generateReceipt();
    }
}