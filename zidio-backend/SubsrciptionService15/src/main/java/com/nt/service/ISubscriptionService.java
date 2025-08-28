package com.nt.service;

import java.util.List;

import com.nt.dto.SubscriptionDto;

public interface ISubscriptionService {
	public String createSubscription(SubscriptionDto dto);
	public List<SubscriptionDto> getAllSubscriptions();
	public SubscriptionDto getSubscriptionById(Long id);
}
