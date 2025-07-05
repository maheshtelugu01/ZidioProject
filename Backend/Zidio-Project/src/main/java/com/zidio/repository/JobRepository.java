package com.zidio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zidio.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	List<Job> findByTitle(String title);
	List<Job> findByCompanyName(String companyName);
	//List<Job> findByPostedBy(String recuiterEmail);
	
}
