package com.classisfication.DocClassification.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="DOCUMENT")
@Getter
@Setter
public class Document {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="document_name")
	private String documentName;
	
	@Column(name="document_size")
	private Long documentSize;
	
	@Column(name="file_type")
	private String file_type;
	
	@Lob
	@Column(name="file_content")
	@JsonIgnore
	private byte[] fileContent;
	
	@ManyToOne
	@JoinColumn(name = "classification_request_id")
	@JsonIgnore
	private ClassificationRequest classificationRequest;
	

}
