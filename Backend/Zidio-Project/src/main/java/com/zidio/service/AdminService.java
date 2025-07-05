package com.zidio.service;

import java.util.List;

import com.zidio.dto.UserInfoResponse;
import com.zidio.dto.UserStatusUpdateRequest;
import com.zidio.entity.UserInfo;

public interface AdminService {
	public UserInfoResponse updateUserStatus(UserStatusUpdateRequest req);
	public List<UserInfoResponse>getAllUsers();
	public List<UserInfoResponse>getUsersByRole(String role);
	public UserInfoResponse toResponse(UserInfo user);
	
}
