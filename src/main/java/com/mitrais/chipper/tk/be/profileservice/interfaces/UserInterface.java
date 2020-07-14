package com.mitrais.chipper.tk.be.profileservice.interfaces;

import com.mitrais.chipper.tk.be.profileservice.interfaces.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@FeignClient(name = "backend-service")
@RequestMapping(value = "/api")
public interface UserInterface {
    @RequestMapping(value = "/users/{id}")
    Optional<UserResponseDto> fetchUserbyId(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable("id") Long id);
}