package com.mitrais.chipper.tk.be.profileservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.chipper.tk.be.profileservice.dto.response.LegacyProfileResponseDTO;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;
import com.mitrais.chipper.tk.be.profileservice.exception.BadRequestException;
import com.mitrais.chipper.tk.be.profileservice.feign.LegacyFeignClient;
import com.mitrais.chipper.tk.be.profileservice.repository.ProfileRepository;
import com.mitrais.chipper.tk.be.profileservice.service.MigrateService;

@Service
public class MigrateServiceImpl implements MigrateService {

	private ProfileRepository profileRepository;
	private LegacyFeignClient legacyFeignClient;

	@Autowired
	MigrateServiceImpl(ProfileRepository profileRepository, LegacyFeignClient legacyFeignClient) {
		this.profileRepository = profileRepository;
		this.legacyFeignClient = legacyFeignClient;
	}

	@Override
	public void fetchAndSaveFromLegacy(String header, Long userId) {
		List<LegacyProfileResponseDTO> profiles = legacyFeignClient.fetchProfileDataFromLegacy(header)
				.orElseThrow(() -> new BadRequestException("Error: No profile found!"));
		List<Profile> batchProfile = new ArrayList<>();
		profiles.forEach(p -> {
			Profile profile = new Profile();
			profile.setAboutMe(p.getAboutMe());
			profile.setCity(p.getCity());
			profile.setDeleted(false);
			profile.setDob(p.getDob());
			profile.setFullName(p.getFullName());
			profile.setGender(p.getGender());
			profile.setId(p.getProfileId());
			profile.setInterest(p.getInterest());
			profile.setPhotoProfile(p.getPhotoProfile());
			profile.setPhotoProfileFilename(p.getPhotoProfileFilename());
			profile.setUserId(p.getUserId());
			profile.setCreatedBy(p.getCreatedBy());
			profile.setCreatedDate(p.getCreatedDate());
			profile.setLastModifiedBy(p.getLastModifiedBy());
			profile.setLastModifiedDate(p.getLastModifiedDate());
			batchProfile.add(profile);
		});
		profileRepository.saveAll(batchProfile);
	}
}
