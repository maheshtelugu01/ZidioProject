package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.SubscriptionPlan;

public interface SubscriptionRepository extends JpaRepository<SubscriptionPlan, Long> {
	 
	
}
