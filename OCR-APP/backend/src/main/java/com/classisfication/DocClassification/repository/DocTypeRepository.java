package com.classisfication.DocClassification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classisfication.DocClassification.entity.DocType;

@Repository
public interface DocTypeRepository extends JpaRepository<DocType,Long>{
	List<DocType> findByCategory(String category);
}
