package com.mitrais.chipper.tk.be.profileservice.service;

import com.mitrais.chipper.tk.be.profileservice.dto.response.ProfileCreatorResponseDTO;
import com.mitrais.chipper.tk.be.profileservice.dto.response.ProfileByUserIdResponseDTO;

public interface QueryService {
    ProfileByUserIdResponseDTO findByUserId(String header, Long userId);
    ProfileCreatorResponseDTO findOtherPersonProfile(String header, Long userId);
}
