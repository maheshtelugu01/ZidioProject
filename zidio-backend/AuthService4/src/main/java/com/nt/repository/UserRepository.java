package com.nt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User>findByEmail(String email);
	@Query("SELECT u.email FROM User u")
	List<String>findAllEmails();
}
