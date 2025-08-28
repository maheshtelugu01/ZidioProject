package com.nt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionStatusDto {
	private Long id;
	private String name;
	private Double price;
	private String description;
	private Integer durationDays;
	private Integer maxApplications;
	private String status;
}
