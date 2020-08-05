package com.mitrais.chipper.tk.be.profileservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.chipper.tk.be.profileservice.dto.response.ProfileMSResponseDTO;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;
import com.mitrais.chipper.tk.be.profileservice.exception.BadRequestException;
import com.mitrais.chipper.tk.be.profileservice.repository.ProfileRepository;
import com.mitrais.chipper.tk.be.profileservice.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public ProfileMSResponseDTO findByUserId(Long userId) {
		Profile profile = profileRepository.findByUserId(userId)
				.orElseThrow(() -> new BadRequestException("No profile with user id : " + userId));
		ProfileMSResponseDTO response = new ProfileMSResponseDTO(profile);

		return response;

	}

	@Override
	public List<ProfileMSResponseDTO> findAllProfiles() {
		List<ProfileMSResponseDTO> responses = new ArrayList<>();
		List<Profile> profiles = profileRepository.findAll();
		for (Profile profile : profiles) {
			ProfileMSResponseDTO response = new ProfileMSResponseDTO(profile);
			responses.add(response);
		}

		return responses;

	}

}
