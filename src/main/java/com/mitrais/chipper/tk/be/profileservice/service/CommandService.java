package com.mitrais.chipper.tk.be.profileservice.service;

import org.springframework.web.multipart.MultipartFile;

import com.mitrais.chipper.tk.be.profileservice.dto.request.CreateProfileRequestDTO;

public interface CommandService {
	void create(String header, Long userId, CreateProfileRequestDTO dto);

	void update(String header, Long userId, MultipartFile imageFile, String city, String aboutMe, String interest);

}
