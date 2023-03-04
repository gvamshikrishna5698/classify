package com.classisfication.DocClassification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="doc_type_keyword")
@Getter
@Setter
public class DocTypeKeyword {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@JsonIgnore
	private Long id;
	
	@Column(name="keyword")
	private String keyword;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "doc_type")
	@JsonIgnore
	private DocType docType;

}
