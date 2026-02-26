package Lazy_Demo;

import org.springframework.stereotype.Component;

@Component

public class EagerBean {
	public EagerBean() {
		System.out.println("Eager bean created!!");
	}
	public void start(){
		System.out.println("Eager Bean has been started");
		
	}
	
}
