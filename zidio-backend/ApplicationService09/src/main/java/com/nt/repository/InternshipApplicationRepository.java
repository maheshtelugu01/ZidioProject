package com.nt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.InternshipApplication;

public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Long> {
	Optional<InternshipApplication> findByStudentEmailAndInternshipId(String studentEmail,Long internshipId);
	List<InternshipApplication>findByStudentEmail(String studentEmail);
	List<InternshipApplication>findByInternshipId(Long internshipId);
	List<InternshipApplication>findByInternshipIdIn(List<Long> internshipIds);
	long countByStudentEmail(String email);
}
