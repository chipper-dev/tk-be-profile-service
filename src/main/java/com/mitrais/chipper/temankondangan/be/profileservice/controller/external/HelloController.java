package com.mitrais.chipper.temankondangan.be.profileservice.controller.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.chipper.temankondangan.be.profileservice.interfaces.dto.UserResponseDto;
import com.mitrais.chipper.temankondangan.be.profileservice.security.TokenProvider;
import com.mitrais.chipper.temankondangan.be.profileservice.service.HelloService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    @Autowired
    private HelloService userInterface;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private Environment env;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        return "Hello from Profile Service running at port: " + env.getProperty("local.server.port");
    }

    @GetMapping("/user/{id}")
    public UserResponseDto getUser(HttpServletRequest request, @PathVariable("id") Long id) {
        return userInterface.fetchUser(request.getHeader(HttpHeaders.AUTHORIZATION), id);
    }
}
