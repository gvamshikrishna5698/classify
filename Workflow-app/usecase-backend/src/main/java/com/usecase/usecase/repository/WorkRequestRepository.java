package com.usecase.usecase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usecase.usecase.entity.WorkRequest;

@Repository
public interface WorkRequestRepository extends JpaRepository<WorkRequest, Long>{

}
