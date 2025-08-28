package com.nt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
	Optional<Student> findByEmail(String email);
	@Query("SELECT s.email FROM Student s")
	List<String>findAllEmails();

}
