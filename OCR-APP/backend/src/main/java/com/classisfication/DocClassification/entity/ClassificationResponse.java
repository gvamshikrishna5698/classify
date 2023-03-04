package com.classisfication.DocClassification.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClassificationResponse {

	private String requestOwner;
	
	private String requestCategory;
	
	private long totalKeywords;
	
	private long matchedKeywords;
	
	private long confidence;
	
	private String classificationResult;
	
}
