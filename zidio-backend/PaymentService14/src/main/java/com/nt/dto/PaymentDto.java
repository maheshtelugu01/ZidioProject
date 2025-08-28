package com.nt.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.nt.enums.PaymentStatus;
import com.nt.enums.PaymentType;

import lombok.Data;

@Data
public class PaymentDto {
	private Long id;
	private String email;
	private Long planId;
	private Integer transactionId;
	private Integer amount;
	private String currency;
	private PaymentStatus paymentStatus;
	private PaymentType paymentType;
	private LocalDateTime paymentDate=LocalDateTime.now();
}
