package com.nt.service;

import java.util.List;

import com.nt.dto.RecuiterDto;

public interface IRecuiterService {
	public String saveProfile(RecuiterDto dto);
	public RecuiterDto getByRecuiterEmail(String recuiterEmail);
	public List<RecuiterDto> getAllRecuiters();
	public Long recuiterCount();
	

}
