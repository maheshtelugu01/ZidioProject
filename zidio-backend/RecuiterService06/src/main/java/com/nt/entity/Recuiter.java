package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="RECUITER_TAB")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recuiter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(length=100,nullable=false)
	private String companyName;
	@Column(length=100,nullable=false)
	private String recuiterEmail;
	@Column(length=100,nullable=false)
	private String recuiterName;
	@Column(length=100,nullable=false)
	private String designation;
	@Column(length=100,nullable=false)
	private String linkedInUrl;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
}
