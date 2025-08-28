package com.nt.entity;

import java.io.Serializable;

import com.nt.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="USERS_TAB")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(length=50,unique=true,nullable=false)
	private String email;
	@Column(length=120,nullable=false)
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean active=true;
}
