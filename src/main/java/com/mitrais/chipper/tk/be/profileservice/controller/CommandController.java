package com.mitrais.chipper.tk.be.profileservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mitrais.chipper.tk.be.profileservice.common.CommonResource;
import com.mitrais.chipper.tk.be.profileservice.common.ResponseBody;
import com.mitrais.chipper.tk.be.profileservice.dto.request.CreateProfileRequestDTO;
import com.mitrais.chipper.tk.be.profileservice.security.TokenProvider;
import com.mitrais.chipper.tk.be.profileservice.service.CommandService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "Profile Command Management System")
@RestController
@Validated
@RequestMapping("/")
public class CommandController extends CommonResource {

	@Autowired
	TokenProvider tokenProvider;

	@Autowired
	CommandService commandService;

	@ApiOperation(value = "Create a Profile", response = ResponseEntity.class)
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
	@PostMapping("/create")
	public ResponseEntity<ResponseBody> createProfile(@RequestBody CreateProfileRequestDTO dto,
			HttpServletRequest request) {
		LOGGER.info("Creating a profile");
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		Long userId = tokenProvider.getUserIdFromToken(getToken(header));

		commandService.create(header, userId, dto);
		return ResponseEntity.ok(getResponseBody(HttpStatus.OK.value(), "You have successfully created a profile",
				request.getRequestURI()));
	}

	@ApiOperation(value = "Update Optional Profile", response = ResponseEntity.class)
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
	@PostMapping("/update")
	public ResponseEntity<ResponseBody> update(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "aboutMe", required = false) String aboutMe,
			@RequestParam(value = "interest", required = false) String interest, HttpServletRequest request) {
		LOGGER.info("Update a profile");
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		Long userId = tokenProvider.getUserIdFromToken(getToken(header));

		commandService.update(header, userId, file, city, aboutMe, interest);
		return ResponseEntity.ok(getResponseBody(HttpStatus.OK.value(), "You have successfully updated your profile",
				request.getRequestURI()));
	}
	
}