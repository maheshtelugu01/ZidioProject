package com.nt.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class InternshipDto {
	private Long id;
	private String title;
	private String description;
	private String companyName;
	private String location;
	private LocalDate startDate;
	private Integer durationWeeks;
	private Double stipend;
}
