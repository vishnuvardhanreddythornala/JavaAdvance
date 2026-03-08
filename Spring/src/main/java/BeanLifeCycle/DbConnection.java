package BeanLifeCycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class DbConnection {
	public DbConnection() {
		System.out.println("DB constructor is beign called!");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Init method is beign called!!");
	}
	
	public void executeQuery() {
		System.out.println("Query is being executed");
		System.out.println("SELECT * FROM STUDENTS");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Destroy method is being called");
		
	}

}
