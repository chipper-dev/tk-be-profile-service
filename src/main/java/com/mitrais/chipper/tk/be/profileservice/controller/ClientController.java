package com.mitrais.chipper.tk.be.profileservice.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.chipper.tk.be.profileservice.dto.request.LegacyProfileRequestDTO;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;
import com.mitrais.chipper.tk.be.profileservice.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "Profile Client Management System")
@RestController
@Validated
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;

	@ApiOperation(value = "Find Profile By Id From Client Instance", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
	@GetMapping("/find/{id}")
	public Profile findById(@PathVariable("id") Long id) {
		return clientService.findById(id);

	}

	@ApiOperation(value = "Find Profile By User Id From Client Instance", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
	@GetMapping("/findByUser/{userId}")
	public Profile findByUserId(@PathVariable("userId") Long userId) {
		return clientService.findByUserId(userId);

	}

	@ApiOperation(value = "Find Profile Picture From Client Instance", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
	@GetMapping("/findPhoto/{photoProfileName}")
	public Profile findByPhotoProfileName(@PathVariable("photoProfileName") String photoProfileName)
			throws FileNotFoundException {
		return clientService.findByPhotoProfileName(photoProfileName);

	}

	@ApiOperation(value = "Find All Profiles From Client Instance", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
	@GetMapping("/findall")
	public List<Profile> findAll() {
		return clientService.findAllProfiles();
	}

	@ApiOperation(value = "Save Profile From Client Instance", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
	@PostMapping("/save")
	public Profile save(LegacyProfileRequestDTO requestDTO) {
		return clientService.save(requestDTO);
	}

	@ApiOperation(value = "Delete Profile By User Id From Client Instance", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
	@PutMapping("/deleteByUserId/{userId}")
	public String deleteByUserId(@PathVariable("userId") Long userId) {
		clientService.deleteByUserId(userId);
		return "Successfully deleted profile with user id : " + userId; 
	}
}
