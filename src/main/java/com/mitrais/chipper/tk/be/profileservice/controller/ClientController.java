package com.mitrais.chipper.tk.be.profileservice.controller;

import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.chipper.tk.be.profileservice.dto.request.LegacyProfileRequestDTO;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;
import com.mitrais.chipper.tk.be.profileservice.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@Autowired
	private Environment env;

	@GetMapping("/hello")
	public String index(HttpServletRequest request) {
		return "Hello from Client Profile Service running at port: " + env.getProperty("local.server.port");
	}
	
	@GetMapping("/find/{id}")
	public Profile findById(@PathVariable("id") Long id) {
		return clientService.findById(id);

	}

	@GetMapping("/findByUser/{userId}")
	public Profile findByUserId(@PathVariable("userId") Long userId) {
		return clientService.findByUserId(userId);

	}

	@GetMapping("/find/{photoProfileName}")
	public Profile findByPhotoProfileName(@PathVariable("photoProfileName") String photoProfileName)
			throws FileNotFoundException {
		return clientService.findByPhotoProfileName(photoProfileName);

	}

	@GetMapping("/findall")
	public List<Profile> findAll() {
		return clientService.findAllProfiles();
	}

	@PostMapping("/save")
	public Profile save(LegacyProfileRequestDTO requestDTO) {
		return clientService.save(requestDTO);
	}

	@PutMapping("/deleteByUserId/{userId}")
	public String deleteByUserId(@PathVariable("userId") Long userId) {
		clientService.deleteByUserId(userId);
		return "Successfully deleted profile with user id : " + userId; 
	}
}
