package CaseStudy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new  AnnotationConfigApplicationContext(AppConfig.class);
		
		PaymentService service = context.getBean(PaymentService.class);
		
		service.processPayment(900);
		System.out.println();
		
		service.processPayment(900);
		System.out.println();
		
	}

}
