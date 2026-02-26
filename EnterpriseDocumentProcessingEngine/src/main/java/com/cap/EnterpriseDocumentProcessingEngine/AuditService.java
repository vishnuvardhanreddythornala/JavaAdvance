package com.cap.EnterpriseDocumentProcessingEngine;



import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class AuditService {

	@PostConstruct
	public void init() {
		System.out.println("Audit Service Initialized");
	}
	
	public void logBeforeProcessing(String documentName) {
		 System.out.println("AUDIT: Starting processing for " + documentName);
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("AuditService resources released");
	}
}
