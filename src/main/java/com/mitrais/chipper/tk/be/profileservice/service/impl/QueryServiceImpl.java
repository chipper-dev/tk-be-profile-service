package com.mitrais.chipper.tk.be.profileservice.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mitrais.chipper.tk.be.profileservice.dto.response.ProfileCreatorResponseDTO;
import com.mitrais.chipper.tk.be.profileservice.dto.response.ProfileByUserIdResponseDTO;
import com.mitrais.chipper.tk.be.profileservice.dto.response.LegacyUserResponseDTO;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;
import com.mitrais.chipper.tk.be.profileservice.exception.BadRequestException;
import com.mitrais.chipper.tk.be.profileservice.feign.LegacyFeignClient;
import com.mitrais.chipper.tk.be.profileservice.repository.ProfileRepository;
import com.mitrais.chipper.tk.be.profileservice.service.QueryService;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private LegacyFeignClient legacyFeignClient;

	@Override
	public ProfileByUserIdResponseDTO findByLegacyUser(String header, Long userId) {
		LegacyUserResponseDTO user = legacyFeignClient.fetchUserbyId(header, userId)
				.orElseThrow(() -> new BadRequestException("No user with user id : " + userId));

		Profile profile = profileRepository.findByUserId(userId)
				.orElseThrow(() -> new BadRequestException("No profile with user id : " + userId));

		HashMap<String, Double> rating = legacyFeignClient.getUserRating(header, userId);
		String photoProfileUrl = legacyFeignClient.getImageUrl(header, profile);

		boolean hasPassword = true;
		if (StringUtils.isEmpty(user.getPasswordHashed())) {
			hasPassword = false;
		}

		return ProfileByUserIdResponseDTO.builder().profileId(profile.getId()).fullName(profile.getFullName())
				.dob(profile.getDob()).gender(profile.getGender()).city(profile.getCity()).aboutMe(profile.getAboutMe())
				.interest(profile.getInterest()).email(user.getEmail()).hasPassword(hasPassword)
				.photoProfileUrl(photoProfileUrl).ratingData(rating).build();
	}

	@Override
	public ProfileCreatorResponseDTO findOtherPersonProfile(String header, Long userId) {
		Profile profile = profileRepository.findByUserId(userId)
				.orElseThrow(() -> new BadRequestException("No profile with user id : " + userId));

		Period period = Period.between(profile.getDob(), LocalDate.now());
		String age = String.valueOf(period.getYears());

		HashMap<String, Double> rating = legacyFeignClient.getUserRating(header, userId);
		String photoProfileUrl = legacyFeignClient.getImageUrl(header, profile);

		return ProfileCreatorResponseDTO.builder().fullName(profile.getFullName()).age(age).gender(profile.getGender())
				.aboutMe(profile.getAboutMe()).interest(profile.getInterest()).photoProfileUrl(photoProfileUrl)
				.ratingData(rating).build();
	}
}
