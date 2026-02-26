package Lazy_Demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyConfig.class);
		LazyBean lazyBean = context.getBean(LazyBean.class);
		lazyBean.start();
		context.close();
	}

}
