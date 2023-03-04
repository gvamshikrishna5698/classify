package com.usecase.usecase.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="role")
public class Role {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="role")
	private String role;
	
	@Column(name="role_desctription")
	private String roleDescription;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinTable(name="ROLE_WT", 
			joinColumns = { 
					@JoinColumn(name = "role_id") 
						}, 
			inverseJoinColumns = { 
					@JoinColumn(name = "wi_id") 
						})
	@Getter
	@Setter
	private Set<Worktype> workTypes;

}
