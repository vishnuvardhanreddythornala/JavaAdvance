package com.cap.EnterpriseDocumentProcessingEngine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class StorageService {
	  public StorageService() {
	        System.out.println("StorageService bean created");
	    }

	    public void storeDocument(String documentName) {
	        System.out.println("Storing document: " + documentName);
	    }
}
