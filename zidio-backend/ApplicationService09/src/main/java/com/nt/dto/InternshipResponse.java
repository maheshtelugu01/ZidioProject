package com.nt.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InternshipResponse {
	private Long id;
	private String title;
	private String description;
	private String companyName;
	private String location;
	private LocalDate startDate;
	private Integer durationWeeks;
	private Double stipend;
	private String status;
}
