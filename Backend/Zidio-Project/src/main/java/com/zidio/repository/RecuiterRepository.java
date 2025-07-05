package com.zidio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zidio.entity.Recuiter;

public interface RecuiterRepository extends JpaRepository<Recuiter, Long> {
	Optional<Recuiter> findByRecuiterEmail(String recuiterEmail);
}
