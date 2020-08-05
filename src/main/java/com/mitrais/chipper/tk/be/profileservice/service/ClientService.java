package com.mitrais.chipper.tk.be.profileservice.service;

import java.util.List;

import com.mitrais.chipper.tk.be.profileservice.dto.response.ProfileMSResponseDTO;

public interface ClientService {

	public ProfileMSResponseDTO findByUserId(Long userId);

	List<ProfileMSResponseDTO> findAllProfiles();
}
