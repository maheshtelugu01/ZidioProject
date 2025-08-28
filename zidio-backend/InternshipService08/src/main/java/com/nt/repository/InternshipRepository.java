package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.Internship;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
	List<Internship> findByTitle(String title);
	List<Internship> findByCompanyName(String companyName);
	@Query("SELECT i FROM Internship i WHERE i.postedBy.email=:recuiterEmail AND i.deleted=false")
	List<Internship> findInternshipsByRecuiterEmail(String  recuiterEmail);
	@Query("SELECT i.id FROM Internship i WHERE i.postedBy.email=:recuiterEmail AND i.deleted=false")
	List<Long> findInternshipIdsByRecuiterEmail(String  recuiterEmail);
	@Query("SELECT count(i) FROM Internship i WHERE i.postedBy.email=:email AND i.deleted=false")
	Long countInternshipsByUserEmail(String  email);

}
