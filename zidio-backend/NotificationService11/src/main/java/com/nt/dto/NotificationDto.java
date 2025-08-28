package com.nt.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class NotificationDto implements Serializable {
	private Long id;
	@NonNull
	private String email;
	@NonNull
	private String message;
	@NonNull
	private String source;
	private LocalDateTime createdAt=LocalDateTime.now();
} 
