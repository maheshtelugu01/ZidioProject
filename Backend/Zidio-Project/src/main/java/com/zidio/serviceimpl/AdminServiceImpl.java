package com.zidio.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidio.dto.UserInfoResponse;
import com.zidio.dto.UserStatusUpdateRequest;
import com.zidio.entity.UserInfo;
import com.zidio.repository.AdminRepository;
import com.zidio.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepo;
	@Override
	public UserInfoResponse updateUserStatus(UserStatusUpdateRequest req) {
		UserInfo user=adminRepo.findByEmail(req.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
		user.setActive(req.isActive());
		UserInfo saved=adminRepo.save(user);
		return toResponse(saved);
	}

	@Override
	public List<UserInfoResponse> getAllUsers() {
		return adminRepo.findAll().stream().map(this::toResponse).collect(Collectors.toList());
	}

	@Override
	public List<UserInfoResponse> getUsersByRole(String role) {
		return adminRepo.findByRole(role.toUpperCase()).stream().map(this::toResponse).collect(Collectors.toList());
	}

	@Override
	public UserInfoResponse toResponse(UserInfo user) {
		return new UserInfoResponse(user.getEmail(),user.getRole(),user.isActive());
	}

}
