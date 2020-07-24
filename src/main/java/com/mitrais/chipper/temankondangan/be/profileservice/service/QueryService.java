package com.mitrais.chipper.temankondangan.be.profileservice.service;

import com.mitrais.chipper.temankondangan.be.profileservice.controller.dto.response.ProfileCreatorResponseDto;
import com.mitrais.chipper.temankondangan.be.profileservice.controller.dto.response.ProfileResponseDto;

public interface QueryService {
    ProfileResponseDto findByUserId(String token, Long userId);
    ProfileCreatorResponseDto findOtherPersonProfile(String token, Long userId);
}
