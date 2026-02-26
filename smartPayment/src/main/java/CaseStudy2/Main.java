package CaseStudy2;




import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n--- Context Started ---\n");

        PaymentProcessor processor = context.getBean(PaymentProcessor.class);
        processor.makePayment(2500);

        System.out.println("\n--- Prototype Check ---\n");

        UpiPayment upi1 = context.getBean(UpiPayment.class);
        UpiPayment upi2 = context.getBean(UpiPayment.class);

       

        System.out.println("\n--- Closing Context ---\n");
        context.close();
    }
}