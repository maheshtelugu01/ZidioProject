package com.zidio.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserStatusUpdateRequest {
	private String email;
	private boolean isActive;
}
