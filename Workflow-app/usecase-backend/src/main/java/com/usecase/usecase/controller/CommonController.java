package com.usecase.usecase.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usecase.usecase.entity.WorkRequest;
import com.usecase.usecase.repository.WorkRequestRepository;
import com.usecase.usecase.service.CommonService;
import com.usecase.usecase.util.CommonUtil;

@RestController
@RequestMapping("/usecase/create")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080","http://localhost:4401"})
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	

	@Autowired
	private WorkRequestRepository workRequestRepository;
	
	@Autowired
	private CommonUtil commonUtil;

	@PostMapping("/createWorkRequest")
	public Long createWorkRequest(
			@RequestBody Map<String, Object> map
			){
		WorkRequest request = commonUtil.createRequestObjectFromString(map.get("request").toString());
		request.setWorkType(map.get("worktype").toString());
		//request.setDocuments(commonUtil.cresdateDocumentObjectFromString(map.get("request").toString()));
		request.setStatus("OPEN");
		request.setSourceId(map.get("source_id").toString());
		return workRequestRepository.save(request).getId();
	}
	
	@GetMapping("/getWorkRequest")
	public List<WorkRequest> getWorkRequest(@RequestParam("username") String username){
		return commonService.getWorkRequest(username);
	}
	
	@PostMapping("/saveWorkRequest")
	public WorkRequest saveWorkRequest(@RequestBody WorkRequest request) {
		request.setActionComment("SAVE");
		request.setStatus("CLOSED");
		return workRequestRepository.save(request);
	}
	
	@GetMapping("/getWorkRequest/{id}")
	public WorkRequest getWorkRequest(@PathVariable("id") Long id) {
		return commonService.getWorkRequest(id);
	}
	
//	@GetMapping(value = "/getDocument/{id}")
//	public ResponseEntity<byte[]> get(@PathVariable Long id) {
//	    HttpHeaders headers = new HttpHeaders();
//	    Document doc = documentRepository.findById(id).get();
//	    if(doc.getFile_type().equals("pdf")) {
//	    	headers.setContentType(MediaType.APPLICATION_PDF);
//	    }else if(doc.getFile_type().equalsIgnoreCase("jpeg")) {
//	    	headers.setContentType(MediaType.IMAGE_JPEG);
//	    }else if(doc.getFile_type().equalsIgnoreCase("png")) {
//	    	headers.setContentType(MediaType.IMAGE_PNG);
//	    }
//	    return new ResponseEntity<>(doc.getFileContent(), headers, HttpStatus.OK);
//	}
}
