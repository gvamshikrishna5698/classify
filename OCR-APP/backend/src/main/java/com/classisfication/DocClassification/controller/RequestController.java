package com.classisfication.DocClassification.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.classisfication.DocClassification.entity.ClassificationRequest;
import com.classisfication.DocClassification.entity.Document;
import com.classisfication.DocClassification.repository.DocumentRepository;
import com.classisfication.DocClassification.repository.RequestRepository;
import com.classisfication.DocClassification.service.RequestService;


@RestController
@RequestMapping("/create")
@CrossOrigin(origins={"http://localhost:4200","http://someserver:8080", "http://localhost:4401"})
public class RequestController {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private RequestRepository requestRepositry;

	@PostMapping(value = "/creteForFile")
	public ClassificationRequest createRequest(
			 @RequestPart(value="files") MultipartFile[] files,
			 @RequestPart(value="request") String request
			 ) {
		
		return requestService.createRequest(files, request);
	}
	
	@PostMapping(value = "/getTokensFromFile")
	public Set<String> getTokens(@RequestPart(value="file") MultipartFile file){
		return requestService.getTokens(file);
	}
	
	@PostMapping (value="/createRequestFromText")
	public ClassificationRequest classifyFromText(@RequestBody ClassificationRequest request){
		
		return requestService.createRequestFromText(request);
	}
	
	@GetMapping(value = "/getDocument/{id}")
	public ResponseEntity<byte[]> get(@PathVariable Long id) {
	    HttpHeaders headers = new HttpHeaders();
	    Document doc = documentRepository.findById(id).get();
	    if(doc.getFile_type().equals("pdf")) {
	    	headers.setContentType(MediaType.APPLICATION_PDF);
	    }else if(doc.getFile_type().equalsIgnoreCase("jpeg")) {
	    	headers.setContentType(MediaType.IMAGE_JPEG);
	    }else if(doc.getFile_type().equalsIgnoreCase("png")) {
	    	headers.setContentType(MediaType.IMAGE_PNG);
	    }
	    return new ResponseEntity<>(doc.getFileContent(), headers, HttpStatus.OK);
	}
	
	@GetMapping(value="/getDocuments/{id}")
	public List<Long> getDocId(@PathVariable("id")Long id){
		ClassificationRequest request = requestRepositry.findById(id).get();
		List<Long> list = new ArrayList<Long>();
		request.getDocuments().forEach(el -> list.add(el.getId()));
		return list;
	}
	
}
