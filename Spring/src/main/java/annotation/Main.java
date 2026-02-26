package annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		MessageService messageService = context.getBean(MessageService.class);
		messageService.sendMessage();
		
	
		context.close();
	}

}

