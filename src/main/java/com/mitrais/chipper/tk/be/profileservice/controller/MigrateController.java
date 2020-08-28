package com.mitrais.chipper.tk.be.profileservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.chipper.tk.be.profileservice.common.CommonResource;
import com.mitrais.chipper.tk.be.profileservice.common.ResponseBody;
import com.mitrais.chipper.tk.be.profileservice.security.TokenProvider;
import com.mitrais.chipper.tk.be.profileservice.service.MigrateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "Profile Migrate Management System")
@RestController
@Validated
@RequestMapping("/migrate")
public class MigrateController extends CommonResource {

	@Autowired
	TokenProvider tokenProvider;

	@Autowired
	MigrateService migrateService;

	@Autowired
	private Environment env;
	
	@ApiOperation(value = "Get Profile From Token", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
	@GetMapping("/hello")
	public String index(HttpServletRequest request) {
		return "Hello from Profile Service running at port: " + env.getProperty("local.server.port");
	}
	
	@ApiOperation(value = "Fetch All Data From Legacy And Save In Profile Microservice", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
	@PostMapping("/fetchAndSaveDataFromLegacy")
	public ResponseEntity<ResponseBody> fetchDBFromLegacy(HttpServletRequest request) {
		LOGGER.info("Fetch and save all data from legacy profile to microservice profile");
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		Long userId = tokenProvider.getUserIdFromToken(getToken(header));

		migrateService.fetchAndSaveFromLegacy(header, userId);
		return ResponseEntity.ok(getResponseBody(HttpStatus.OK.value(), "You have fetch and save all data from legacy profile",
				request.getRequestURI()));
	}
}