package com.mitrais.chipper.tk.be.profileservice.feign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.chipper.tk.be.profileservice.dto.response.ProfileMSResponseDTO;
import com.mitrais.chipper.tk.be.profileservice.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;

	@GetMapping("/find/{userId}")
	public ProfileMSResponseDTO findByUserId(@PathVariable("userId") Long userId) {
		return clientService.findByUserId(userId);

	}
	
	@GetMapping("/profiles")
	public List<ProfileMSResponseDTO> findAllProfiles() {
		return clientService.findAllProfiles();
	}
}
