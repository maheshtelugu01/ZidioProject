package com.nt.service;

import java.util.List;

import com.nt.dto.UserStatusUpdateRequest;
import com.nt.entity.User;

public interface IAdminService {
	public String activateUser(UserStatusUpdateRequest req);
	public List<User>getAllUsers();	
	public String deactivateUser(UserStatusUpdateRequest req);

	
}
