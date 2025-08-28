package com.nt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
	Optional<JobApplication> findByStudentEmailAndJobId(String studentEmail,Long jobId);
	List<JobApplication>findByStudentEmail(String studentEmail);
	List<JobApplication>findByJobId(Long jobId);
	List<JobApplication>findByJobIdIn(List<Long> jobIds);
	long countByStudentEmail(String studentEmail);
	
}
