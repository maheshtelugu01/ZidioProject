package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification>findByEmail(String email);
}
