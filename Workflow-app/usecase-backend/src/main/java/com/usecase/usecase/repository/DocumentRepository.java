package com.usecase.usecase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usecase.usecase.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
