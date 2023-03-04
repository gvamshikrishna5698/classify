package com.usecase.usecase.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usecase.usecase.entity.Document;
import com.usecase.usecase.entity.Role;
import com.usecase.usecase.entity.User;
import com.usecase.usecase.entity.WorkRequest;
import com.usecase.usecase.entity.Worktype;
import com.usecase.usecase.repository.UserRepository;
import com.usecase.usecase.repository.WorkRequestRepository;

@Service
public class CommonService {
	
	@Autowired
	private WorkRequestRepository workRequestRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	 EntityManager entityManager;
	
	
	public Long craeteRequest(String text, MultipartFile[] files){
		WorkRequest request = createRequestObjectFromString(text);
		request.setStatus("OPEN");
		populateDocuments(files, request);
		workRequestRepository.save(request);
		return request.getId();
	}
	
	
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
	
	public File convertToJavaFile(MultipartFile file) {
		 File convFile = new File( file.getOriginalFilename() );
		    FileOutputStream fos;
			try {
				fos = new FileOutputStream( convFile );
				fos.write( file.getBytes() );
			    fos.close();
			}catch (IOException e ) {

			}
		return convFile;
	}
	
	public void populateDocuments(MultipartFile[] files, WorkRequest request){
		for(MultipartFile file: files) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		    Document doc = new Document();
		    doc.setDocumentName(fileName);
		    doc.setDocumentSize(file.getSize());
//		    try {
//				doc.setFileContent(file.getBytes());
//				doc.setFile_type(StringUtils.getFilenameExtension(file.getOriginalFilename()));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		    request.add(doc);
		}
	}



	public List<WorkRequest> getWorkRequest(String username) {
		User user = userRepository.findByUsername(username);
		List<String> workTypes = new ArrayList<String>();
		Set<Role> roles = user.getRoles();
		
		for(Role r : roles)
			for(Worktype wt: r.getWorkTypes())
				workTypes.add(wt.getWorktype());
		String jpql = "from WorkRequest where work_type in (:names) and status='OPEN'";
		Query q = entityManager.createQuery(jpql);
		q.setParameter("names", workTypes);
		@SuppressWarnings("unchecked")
		List<WorkRequest> requests = q.getResultList();
		return requests;
	}
	
	public WorkRequest getWorkRequest(Long id) {
		return workRequestRepository.findById(id).get();
	}
}
