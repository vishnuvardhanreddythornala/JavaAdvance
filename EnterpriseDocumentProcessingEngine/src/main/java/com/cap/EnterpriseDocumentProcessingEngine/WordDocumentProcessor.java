package com.cap.EnterpriseDocumentProcessingEngine;

import org.springframework.context.annotation.Lazy;

import org.springframework.stereotype.Component;

@Component
@Lazy
public class WordDocumentProcessor implements DocumentProcessor {
	
	public WordDocumentProcessor() {
		System.out.println("Word Document bean created");
	}
	@Override
	public void processDocument(String documentName) {
		System.out.println("Processing Word Document: "+documentName);
		
	}

}
