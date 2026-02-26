package BeanLifeCycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Main {
	public static void main(String[] args) {
		System.out.println("Container Starting \n");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(lifeCycleConfig.class);
		
		System.out.println("\n Using Bean");
		DbConnection DbConnection = context.getBean(DbConnection.class);
		DbConnection.executeQuery();
		
		System.out.println("\n Container Closing");
		context.close();
	}

}
