package com.mitrais.chipper.tk.be.profileservice.service.impl;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.chipper.tk.be.profileservice.dto.request.LegacyProfileRequestDTO;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;
import com.mitrais.chipper.tk.be.profileservice.exception.BadRequestException;
import com.mitrais.chipper.tk.be.profileservice.repository.ProfileRepository;
import com.mitrais.chipper.tk.be.profileservice.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public Profile findByUserId(Long userId) {
		return profileRepository.findByUserId(userId)
				.orElseThrow(() -> new BadRequestException("No profile with user id : " + userId));
	}

	@Override
	public List<Profile> findAllProfiles() {
		return profileRepository.findAll();
	}

	@Override
	public Profile findById(Long id) {
		return profileRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("No profile with id : " + id));
	}

	@Override
	public Profile findByPhotoProfileName(String photoProfileName) throws FileNotFoundException {
		return profileRepository.findByPhotoProfileFilename(photoProfileName)
				.orElseThrow(() -> new FileNotFoundException("File not found with filename: " + photoProfileName));
	}

	@Override
	public Profile save(LegacyProfileRequestDTO requestDTO) {
		Profile profile = Profile.builder().aboutMe(requestDTO.getAboutMe()).city(requestDTO.getCity()).deleted(false)
				.dob(requestDTO.getDob()).fullName(requestDTO.getFullName()).gender(requestDTO.getGender())
				.interest(requestDTO.getInterest()).photoProfile(requestDTO.getPhotoProfile())
				.photoProfileFilename(requestDTO.getPhotoProfileFilename()).userId(requestDTO.getUserId()).build();
		profileRepository.save(profile);
		return profile;
	}

	@Override
	public void deleteByUserId(Long userId) {
		Profile profile = profileRepository.findByUserId(userId)
				.orElseThrow(() -> new BadRequestException("No profile with user id : " + userId));
		profileRepository.delete(profile);
	}

}
