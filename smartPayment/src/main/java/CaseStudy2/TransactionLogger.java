package CaseStudy2;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class TransactionLogger {
	public TransactionLogger() {
        System.out.println("TransactionLogger bean created");
    }
	
	@PostConstruct
    public void init() {
        System.out.println("Logger initialized");
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Logger destroyed");
    }
}
