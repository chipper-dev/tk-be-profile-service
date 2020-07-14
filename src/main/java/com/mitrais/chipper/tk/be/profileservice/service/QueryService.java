package com.mitrais.chipper.tk.be.profileservice.service;

import com.mitrais.chipper.tk.be.profileservice.controller.dto.response.ProfileCreatorResponseDto;
import com.mitrais.chipper.tk.be.profileservice.controller.dto.response.ProfileResponseDto;

public interface QueryService {
    ProfileResponseDto findByUserId(String token, Long userId);
    ProfileCreatorResponseDto findOtherPersonProfile(String token, Long userId);
}
