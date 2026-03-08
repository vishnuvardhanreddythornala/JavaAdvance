package com.cap.EnterpriseDocumentProcessingEngine;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 DocumentEngine engine = context.getBean(DocumentEngine.class);

	     engine.execute("enterprise-data.xml");

	     context.close();
	}
}
