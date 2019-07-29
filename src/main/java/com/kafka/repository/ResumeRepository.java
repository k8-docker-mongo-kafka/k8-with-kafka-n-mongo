package com.kafka.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kafka.model.ResumeEvent;

@Repository
public interface ResumeRepository extends MongoRepository<ResumeEvent, String> {

	List<ResumeEvent> getByEmpId(String empId);
}
