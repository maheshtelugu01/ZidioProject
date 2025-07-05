package com.zidio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zidio.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	Optional<Application> findByStudentEmailAndJobId(String studentEmail,Long jobId);
	List<Application>findByStudentEmail(String studentEmail);
	List<Application>findByJobId(Long jobId);
}
