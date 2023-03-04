package com.classisfication.DocClassification.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.classisfication.DocClassification.constants.RequestConstants;
import com.classisfication.DocClassification.entity.ClassificationRequest;
import com.classisfication.DocClassification.entity.ClassificationResponse;
import com.classisfication.DocClassification.entity.DocType;
import com.classisfication.DocClassification.entity.Document;
import com.classisfication.DocClassification.repository.DocTypeRepository;
import com.classisfication.DocClassification.repository.RequestRepository;
import com.classisfication.DocClassification.utils.CommonUtil;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private OcrService ocrService;
	
	@Autowired
	private DocTypeRepository docTypeRepository;

	@Override
	public ClassificationRequest createRequest(MultipartFile[] files, String req) {
		ClassificationRequest request = commonUtil.createRequestObjectFromString(req);
		getDocuments(files, request);
		request.setRequestStatus(RequestConstants.REQUEST_OPEN);
		request.setClassificationResult("IN_PROGESS");
		request.setTargetReferenceNumber("NOT_YET_CREATED");
		request.setRequestType(RequestConstants.QUICK_RESPONSE);
		requestRepository.save(request);
		return request;
	}
	
	
	public ClassificationRequest findDocType(ClassificationRequest request, String text) {
		
		List<DocType> docTypes = docTypeRepository.findByCategory(request.getRequestCategory());
		long confidence = 0;
		long totalKeywords = 0;
		long matchedKeywords = 0;
		DocType docType = null;
		
		for(DocType type : docTypes) {
			long tmpTotalKeywords = type.getKeywords().size();
			long tmpMatchedKeywords = type.getKeywords().stream().filter(key -> text.contains(key.getKeyword().toUpperCase())).count();
			long tmpConfidence  = (long)((tmpMatchedKeywords * 1.0 / tmpTotalKeywords) * 100);
			if(tmpConfidence > confidence ) {
				confidence = tmpConfidence;
				matchedKeywords = tmpMatchedKeywords;
				totalKeywords = tmpTotalKeywords;
				docType = type;
			}
		}
		
		if(confidence >= 50) {
			request.setConfidence(confidence);
			request.setMatchedKeywords(matchedKeywords);
			request.setTotalKeywords(totalKeywords);
			request.setClassificationResult(docType.getDocTypeName());
		}else {
			request.setConfidence(0);
			request.setMatchedKeywords(0);
			request.setTotalKeywords(0);
			request.setClassificationResult("UNCLASSIFIED");
		}
		
		return request;
	}
	
	public String processDocuments(MultipartFile[] files, ClassificationRequest request){
		
		StringBuffer sb = new StringBuffer();
		for(MultipartFile file : files) {
			String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
			if(RequestConstants.OCR_SUPPORTED_FORMATS.contains(ext)) {
				sb.append(ocrService.getTextFromFile(convertToJavaFile(file)));
			}else if(ext.equalsIgnoreCase("txt")) {
				sb.append(ocrService.readTextFile(convertToJavaFile(file)));
			}
		}
		
		return sb.toString();
	}
	
	public File convertToJavaFile(MultipartFile file) {
		 File convFile = new File( file.getOriginalFilename() );
		    FileOutputStream fos;
			try {
				fos = new FileOutputStream( convFile );
				fos.write( file.getBytes() );
			    fos.close();
			}catch (IOException e ) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		return convFile;
	}

	public void getDocuments(MultipartFile[] files, ClassificationRequest request){
		for(MultipartFile file: files) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		    Document doc = new Document();
		    doc.setDocumentName(fileName);
		    doc.setDocumentSize(file.getSize());
		    try {
				doc.setFileContent(file.getBytes());
				doc.setFile_type(StringUtils.getFilenameExtension(file.getOriginalFilename()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		  
		    request.add(doc);
		}
		
	}

	@Override
	public Set<String> getTokens(MultipartFile file) {
		String text = ocrService.getTextFromFile(convertToJavaFile(file));
		text = text.replaceAll("[^a-zA-Z0-9 ]", "");
		 Set<String> hashSet = Arrays.asList(text.split(" ")).stream().filter(str -> str.length() > 2).collect(Collectors.toSet());
		return hashSet;
	}

	@Override
	public ClassificationRequest createRequestFromText(ClassificationRequest request) {
		 return findDocType(request, request.getTextContent().toUpperCase());	
	}

}
