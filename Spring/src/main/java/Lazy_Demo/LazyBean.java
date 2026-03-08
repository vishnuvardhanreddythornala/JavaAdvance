package Lazy_Demo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyBean {
	public LazyBean() {
		System.out.println("Lazy bean created!!");
	}
	public void start(){
		System.out.println("Lazy Bean has been started");
		
	}

}
