package com.usecase.usecase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="work_request")
public class WorkRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="reference_number")
	private String referenceNumber;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="work_type")
	private String workType;
	
	@Column(name="status")
	private String status;
	
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;
	
	@Column(name="action_comment")
	private String actionComment;
	
	@Column(name="source_id")
	private String sourceId;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="workRequest")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Document> documents;
	
	public void add(Document document) {
		if(documents == null) {
			this.documents = new ArrayList<Document>();
		}
		documents.add(document);
		document.setWorkRequest(this);
	}
	
}
