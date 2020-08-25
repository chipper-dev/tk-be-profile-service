package com.mitrais.chipper.tk.be.profileservice.feign;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mitrais.chipper.tk.be.profileservice.dto.request.LegacyProfileRequestDTO;
import com.mitrais.chipper.tk.be.profileservice.dto.response.LegacyUserResponseDTO;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;

@FeignClient(name = "legacy-service")
@RequestMapping(value = "/legacy")
public interface LegacyFeignClient {
	
	//fetch legacy profile data
	@GetMapping(value = "/db/allprofiles")
    Optional<List<LegacyProfileRequestDTO>> fetchProfileDataFromLegacy(@RequestHeader(HttpHeaders.AUTHORIZATION) String header);
    
	// user service
    @GetMapping(value = "/user/{id}")
    Optional<LegacyUserResponseDTO> fetchUserbyId(@RequestHeader(HttpHeaders.AUTHORIZATION) String header, @PathVariable("id") Long id);
    
    @GetMapping(value = "/user/{email}")
    Optional<LegacyUserResponseDTO> fetchUserbyEmail(@RequestHeader(HttpHeaders.AUTHORIZATION) String header, @PathVariable("email") String email);
    
    // image service
    @PostMapping(value = "/image/url")
	String getImageUrl(@RequestHeader(HttpHeaders.AUTHORIZATION) String header, @RequestBody Profile profile);
    
    // rating service
    @GetMapping(value = "/rating/{userId}")
    HashMap<String, Double> getUserRating(@RequestHeader(HttpHeaders.AUTHORIZATION) String header, @PathVariable("userId") Long userId);
    
}
