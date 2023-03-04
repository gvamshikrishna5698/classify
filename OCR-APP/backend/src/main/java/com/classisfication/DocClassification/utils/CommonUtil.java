package com.classisfication.DocClassification.utils;

import org.springframework.stereotype.Component;

import com.classisfication.DocClassification.entity.ClassificationRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonUtil {
	
	public ClassificationRequest createRequestObjectFromString(String obj) {
		
		ClassificationRequest crq = new ClassificationRequest();
		
		try {
			ObjectMapper mapper  = new ObjectMapper();
			crq = mapper.readValue(obj, ClassificationRequest.class);
			mapper.configure( JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return crq;
	}
}
