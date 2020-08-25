package com.mitrais.chipper.tk.be.profileservice.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mitrais.chipper.tk.be.profileservice.dto.request.CreateProfileRequestDTO;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;
import com.mitrais.chipper.tk.be.profileservice.exception.BadRequestException;
import com.mitrais.chipper.tk.be.profileservice.repository.ProfileRepository;
import com.mitrais.chipper.tk.be.profileservice.service.CommandService;

@Service
public class CommandServiceImpl implements CommandService {

	private ProfileRepository profileRepository;

	@Autowired
	CommandServiceImpl(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Override
	public void create(String header, Long userId, CreateProfileRequestDTO requestDTO) {
		// check dob valid
		LocalDate dob;
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
		try {
			dob = LocalDate.parse(requestDTO.getDob(), df);
		} catch (Exception e) {
			throw new BadRequestException("Error: Date not valid!");
		}

		// check age over 18
		if (Period.between(dob, LocalDate.now()).getYears() < 18) {
			throw new BadRequestException("Error: Age should not under 18!");
		}

		Profile profile = profileRepository.findByUserId(userId).orElse(new Profile());
		profile.setUserId(userId);
		profile.setFullName(requestDTO.getFullname());
		profile.setDob(dob);
		profile.setGender(requestDTO.getGender());
		profile.setDeleted(false);
		profileRepository.save(profile);
	}

	@Override
	public void update(String header, Long userId, MultipartFile imageFile, String city, String aboutMe,
			String interest) {

		Profile profile = profileRepository.findByUserId(userId)
				.orElseThrow(() -> new BadRequestException("Error: User not found!"));

		String[] allowedFormatImage = { "jpeg", "png", "jpg" };
		List<String> allowedFormatImageList = Arrays.asList(allowedFormatImage);

		if (imageFile != null && !StringUtils.isEmpty(imageFile.getOriginalFilename())) {
			byte[] image = null;
			String fileName = "";
			// throw error if image format is not allowed
			String[] imageFormat = imageFile.getOriginalFilename().split("\\.");
			if (!allowedFormatImageList.contains(imageFormat[imageFormat.length - 1])) {
				throw new BadRequestException("Error: Image format not allowed!");
			}
			try {
				image = imageFile.getBytes();
				fileName = imageFile.getOriginalFilename().replaceAll("\\s+", "-");
			} catch (IOException e) {
				throw new BadRequestException(e.getMessage());
			}
			profile.setPhotoProfile(image);
			profile.setPhotoProfileFilename(fileName);
		}

		profile.setAboutMe(aboutMe);
		profile.setCity(city);
		profile.setInterest(interest);

		profileRepository.save(profile);

	}

}
