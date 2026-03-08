package com.cap.EnterpriseDocumentProcessingEngine;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PdfDocumentProcessor implements DocumentProcessor {
	
	 public PdfDocumentProcessor() {
			System.out.println("PdfDocumentProcessor bean created");
		}

	@Override
	public void processDocument(String documentName) {
		System.out.println("Processing PDF Document: "+documentName);
	}

}
