package com.mitrais.chipper.tk.be.profileservice.service.impl;

import com.mitrais.chipper.tk.be.profileservice.controller.dto.response.ProfileCreatorResponseDto;
import com.mitrais.chipper.tk.be.profileservice.controller.dto.response.ProfileResponseDto;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;
import com.mitrais.chipper.tk.be.profileservice.exception.BadRequestException;
import com.mitrais.chipper.tk.be.profileservice.interfaces.UserInterface;
import com.mitrais.chipper.tk.be.profileservice.interfaces.dto.UserResponseDto;
import com.mitrais.chipper.tk.be.profileservice.repository.ProfileRepository;
import com.mitrais.chipper.tk.be.profileservice.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.Period;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserInterface userInterface;

    @Override
    public ProfileResponseDto findByUserId(String token, Long userId) {
        UserResponseDto user = userInterface.fetchUserbyId(token, userId)
                .orElseThrow(() -> new BadRequestException("No user with user id : " + userId));

        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("No profile with user id : " + userId));

//        HashMap<String, Double> rating = ratingService.getUserRating(userId);
//        String photoProfileUrl = imageService.getImageUrl(profile);

        boolean hasPassword = true;
        if (StringUtils.isEmpty(user.getPasswordHashed())) {
            hasPassword = false;
        }

        return ProfileResponseDto.builder().profileId(profile.getId()).fullName(profile.getFullName())
                .dob(profile.getDob()).gender(profile.getGender()).city(profile.getCity()).aboutMe(profile.getAboutMe())
                .interest(profile.getInterest()).email(user.getEmail()).hasPassword(hasPassword)
//                .photoProfileUrl(photoProfileUrl)
//                .ratingData(rating)
                .build();
    }

    @Override
    public ProfileCreatorResponseDto findOtherPersonProfile(String token, Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("No profile with user id : " + userId));

        Period period = Period.between(profile.getDob(), LocalDate.now());
        String age = String.valueOf(period.getYears());

//        HashMap<String, Double> rating = ratingService.getUserRating(userId);
//        String photoProfileUrl = imageService.getImageUrl(profile);

        return ProfileCreatorResponseDto.builder().fullName(profile.getFullName()).age(age)
                .gender(profile.getGender()).aboutMe(profile.getAboutMe()).interest(profile.getInterest())
//                .photoProfileUrl(photoProfileUrl)
//                .ratingData(rating)
                .build();
    }
}
