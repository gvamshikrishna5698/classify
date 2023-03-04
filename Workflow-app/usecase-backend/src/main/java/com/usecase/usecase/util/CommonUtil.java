package com.usecase.usecase.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usecase.usecase.entity.Document;
import com.usecase.usecase.entity.WorkRequest;

@Component
public class CommonUtil {
	
	public WorkRequest createRequestObjectFromString(String obj) {
		WorkRequest crq = new WorkRequest();
		
		try {
			ObjectMapper mapper  = new ObjectMapper();
			crq = mapper.readValue(obj, WorkRequest.class);
			mapper.configure( JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return crq;
	}
	
	public List<Document> cresdateDocumentObjectFromString(String obj) {
		com.usecase.usecase.entity.Document[] crq = new Document[10];
		try {
			ObjectMapper mapper  = new ObjectMapper();
			crq = mapper.readValue(obj, Document[].class);
			mapper.configure( JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return Arrays.asList(crq);
	}
}
