package com.classisfication.DocClassification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classisfication.DocClassification.entity.DocType;
import com.classisfication.DocClassification.repository.DocTypeRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"http://localhost:4200", "http://someserver:8080","http://localhost:4401"})
public class AdminController {

	@Autowired
	private DocTypeRepository docTypeRepository;

	
	@RequestMapping("/creteDocType")
	public DocType createDocType(@RequestBody DocType docType) {
		docType.getKeywords().forEach(el -> el.setDocType(docType));
		return  docTypeRepository.save(docType);	
	}
	
	@RequestMapping("/getDocType")
	public List<DocType> getDocTypes(){
		return docTypeRepository.findAll();
	}
}
