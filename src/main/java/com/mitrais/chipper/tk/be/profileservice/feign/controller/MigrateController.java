package com.mitrais.chipper.tk.be.profileservice.feign.controller;

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

	@GetMapping("/hello")
	public String index(HttpServletRequest request) {
		LOGGER.info("Hello from Profile Service");
		return "Hello from Profile Service running at port: " + env.getProperty("local.server.port");
	}
	
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