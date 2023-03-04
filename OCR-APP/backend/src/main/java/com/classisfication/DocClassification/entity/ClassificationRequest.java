package com.classisfication.DocClassification.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CLASSIFICATION_REQUEST")
@Getter
@Setter
public class ClassificationRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "request_owner")
	private String requestOwner;
	
	@Column(name = "request_category")
	private String requestCategory;
	
	@Column(name = "request_type")
	private String requestType;
	
	@Column(name = "request_Status")
	private String requestStatus;
	
	@Column(name = "target_url")
	private String targetUrl;
	
	@Column(name = "target_payload")
	private String targetPayload;
	
	@Column(name = "target_reference_number")
	private String targetReferenceNumber;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="classificationRequest", fetch = FetchType.EAGER)
	//@JsonIgnore
	private List<Document> documents;
	
	
	@Column(name="classification_result")
	private String classificationResult;
	
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;
	
	private String textContent;
	
	private long totalKeywords;
	
	private long matchedKeywords;
	
	private long confidence;
	
	public void add(Document document) {
		if(documents == null) {
			this.documents = new ArrayList<Document>();
		}
		documents.add(document);
		document.setClassificationRequest(this);
	}



	public ClassificationRequest() {
		super();
	}
	
}
