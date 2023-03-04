package com.usecase.usecase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="worktype")
public class Worktype {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="worktype")
	private String worktype;
	
	@Column(name="worktype_description")
	private String worktypeDescription;
	

}
