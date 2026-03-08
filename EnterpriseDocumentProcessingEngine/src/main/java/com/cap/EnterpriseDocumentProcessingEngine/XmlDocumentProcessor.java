package com.cap.EnterpriseDocumentProcessingEngine;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class XmlDocumentProcessor implements DocumentProcessor {
	public XmlDocumentProcessor() {
		System.out.println("Xml Document Bean Created");
	}

	@Override
	public void processDocument(String documentName) {
		System.out.println("Processing Xml Document: "+documentName);
		
	}

}
