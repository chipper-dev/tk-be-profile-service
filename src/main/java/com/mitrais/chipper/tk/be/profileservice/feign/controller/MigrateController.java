package com.mitrais.chipper.tk.be.profileservice.feign.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/migrate")
public class MigrateController {

	@Autowired
	private Environment env;

	@GetMapping("/hello")
	public String index(HttpServletRequest request) {
		return "Hello from Profile Service running at port: " + env.getProperty("local.server.port");
	}
	
}