package com.classisfication.DocClassification.service;


import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.classisfication.DocClassification.entity.ClassificationRequest;
import com.classisfication.DocClassification.entity.ClassificationResponse;

@Service
public interface RequestService {
	
	public ClassificationRequest createRequest(MultipartFile[] files, String req);

	public Set<String> getTokens(MultipartFile file);

	public ClassificationRequest createRequestFromText(ClassificationRequest request);
	
	public ClassificationRequest findDocType(ClassificationRequest request,String text);
	
}
