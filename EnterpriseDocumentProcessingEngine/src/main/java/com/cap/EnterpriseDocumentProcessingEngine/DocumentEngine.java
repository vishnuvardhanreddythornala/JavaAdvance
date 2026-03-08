package com.cap.EnterpriseDocumentProcessingEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DocumentEngine {
	 private DocumentProcessor documentProcessor;
	 private AuditService auditService;
	 
	 @Autowired
	 private StorageService storageService;
	 
	 
	 //constructor injection
	 @Autowired
	 public DocumentEngine(@Qualifier("xmlDocumentProcessor") DocumentProcessor documentProcessor) {
		 this.documentProcessor = documentProcessor;
	 }
	 
	 //setter injection
	 @Autowired
	 public void setAuditService(AuditService auditService) {
		 this.auditService = auditService;
	 }
	 
	 public void execute(String documentName) {
		 auditService.logBeforeProcessing(documentName);
		 documentProcessor.processDocument(documentName);
		 storageService.storeDocument(documentName);
	 }
	
	

}
