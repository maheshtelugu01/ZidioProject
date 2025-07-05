package com.zidio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zidio.entity.UserInfo;

public interface AdminRepository extends JpaRepository<UserInfo,Long> {
	Optional<UserInfo>findByEmail(String email);
	List<UserInfo>findByRole(String role);
}
