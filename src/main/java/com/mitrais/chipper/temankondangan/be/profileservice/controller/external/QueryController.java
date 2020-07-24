package com.mitrais.chipper.temankondangan.be.profileservice.controller.external;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.chipper.temankondangan.be.common.response.CommonResource;
import com.mitrais.chipper.temankondangan.be.common.response.ResponseBody;
import com.mitrais.chipper.temankondangan.be.profileservice.controller.dto.response.ProfileCreatorResponseDto;
import com.mitrais.chipper.temankondangan.be.profileservice.controller.dto.response.ProfileResponseDto;
import com.mitrais.chipper.temankondangan.be.profileservice.security.TokenProvider;
import com.mitrais.chipper.temankondangan.be.profileservice.service.QueryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "Profile Management System")
@RestController
@Validated
@RequestMapping("/profile")
public class QueryController extends CommonResource {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    QueryService profileService;

    @ApiOperation(value = "Get Profile From Token", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
    @GetMapping("/find")
    public ResponseEntity<ResponseBody> findByUserId(HttpServletRequest request) {
        LOGGER.info("Find a profile from token");
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = getToken(header);
        Long userId = tokenProvider.getUserIdFromToken(token);

        ProfileResponseDto responseWrapper = profileService.findByUserId(header, userId);
        return ResponseEntity.ok(getResponseBody(HttpStatus.OK.value(), responseWrapper, request.getRequestURI()));
    }

    @ApiOperation(value = "Get Other Person Profile", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
    @GetMapping("/find-profile/{userId}")
    public ResponseEntity<ResponseBody> findProfileCreator(HttpServletRequest request, @PathVariable Long userId) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        ProfileCreatorResponseDto responseWrapper = profileService.findOtherPersonProfile(header, userId);
        return ResponseEntity.ok(getResponseBody(HttpStatus.OK.value(), responseWrapper, request.getRequestURI()));
    }
}
