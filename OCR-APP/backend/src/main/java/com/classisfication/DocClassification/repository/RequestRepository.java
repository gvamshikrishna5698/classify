package com.classisfication.DocClassification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classisfication.DocClassification.entity.ClassificationRequest;

@Repository
public interface RequestRepository extends JpaRepository<ClassificationRequest,Long>{

}
