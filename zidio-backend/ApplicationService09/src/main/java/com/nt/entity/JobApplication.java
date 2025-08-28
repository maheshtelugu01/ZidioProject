package com.nt.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="job_application_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql="UPDATE job_application_tab SET deleted=true WHERE id=?")
@Where(clause="deleted=false")
public class JobApplication {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	 
	private String status="APPLIED";
	private String studentEmail;
	private Long jobId;
	private Boolean deleted=false;
	
}
