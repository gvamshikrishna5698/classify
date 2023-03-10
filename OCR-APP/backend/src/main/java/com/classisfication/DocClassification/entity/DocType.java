package com.classisfication.DocClassification.entity;

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

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name="DOC_TYPE")
public class DocType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="category")
	private String category;
	
	@Column(name="doc_type_name")
	private String docTypeName;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="docType",fetch = FetchType.EAGER)
	private List<DocTypeKeyword> keywords;
	
}
