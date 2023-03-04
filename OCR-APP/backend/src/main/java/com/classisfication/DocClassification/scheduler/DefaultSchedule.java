package com.classisfication.DocClassification.scheduler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.classisfication.DocClassification.entity.ClassificationRequest;
import com.classisfication.DocClassification.entity.Document;
import com.classisfication.DocClassification.repository.RequestRepository;
import com.classisfication.DocClassification.service.OcrService;
import com.classisfication.DocClassification.service.RequestService;

@Component
public class DefaultSchedule {
	
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private OcrService ocrService;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Scheduled(initialDelay = 50, fixedDelay = 5000)
	public void classifyDocs() {
		
		List<ClassificationRequest> pendingRequests = fetchPendingRequests();
		
		for(ClassificationRequest req: pendingRequests) {
			List<File> files= getConvertedFiles(req.getDocuments());
			String text = getText(files);
			req = requestService.findDocType(req, text);
			req.setTargetReferenceNumber(createOutboundRequest(req).toString());
			req.setRequestStatus("SUBMITTED");
			requestRepository.save(req);
		}
		
	}
	
	String createOutboundRequest(ClassificationRequest req) {
		
		String uri = "http://localhost:9030/usecase/create/createWorkRequest";
		  RestTemplate restTemplate=  new RestTemplate();
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      Map<String, Object> map = new HashMap<>();
	      map.put("request", req.getTargetPayload());
	      map.put("documents", req.getDocuments());
		  map.put("worktype", req.getClassificationResult());
		  map.put("source_id", req.getId());

	      HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(map,headers);
	      
	      return restTemplate.exchange(uri, HttpMethod.POST, entity, String.class).getBody();
				

	}
	
	String getText(List<File> files) {
		StringBuffer sb = new StringBuffer();
		for(File f: files) {
			sb.append(ocrService.getTextFromFile(f));
		}
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	List<File> getConvertedFiles(List<Document> document) {
		
		List<File> files = new ArrayList<File>();
		for(Document doc : document) {
			try {
				File file = new File(doc.getDocumentName());
				FileOutputStream fostrm = new FileOutputStream(file);
				fostrm.write(doc.getFileContent());
				files.add(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return files;
	}
	
	List<ClassificationRequest> fetchPendingRequests(){
		String jpql = "from ClassificationRequest where  request_status='CREATED'";
		Query q = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<ClassificationRequest> requests = q.getResultList();
		return requests;
	}

}
