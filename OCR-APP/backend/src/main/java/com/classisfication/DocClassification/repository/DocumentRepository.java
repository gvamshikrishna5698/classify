package com.classisfication.DocClassification.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.classisfication.DocClassification.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long>{

}
