package com.zidio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecuiterDto {
	private Long rid;
	private String companyName;
	private String recruiterEmail;
	private String recuiterName;
	private String designation;
}
