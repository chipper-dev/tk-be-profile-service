package com.mitrais.chipper.tk.be.profileservice.controller;

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

import com.mitrais.chipper.tk.be.profileservice.common.CommonResource;
import com.mitrais.chipper.tk.be.profileservice.common.ResponseBody;
import com.mitrais.chipper.tk.be.profileservice.dto.response.ProfileCreatorResponseDTO;
import com.mitrais.chipper.tk.be.profileservice.dto.response.ProfileByUserIdResponseDTO;
import com.mitrais.chipper.tk.be.profileservice.security.TokenProvider;
import com.mitrais.chipper.tk.be.profileservice.service.QueryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "Profile Query Management System")
@RestController
@Validated
@RequestMapping("/")
public class QueryController extends CommonResource {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    QueryService queryService;

    @ApiOperation(value = "Get Profile From Token", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
    @GetMapping("/find")
    public ResponseEntity<ResponseBody> findByUserId(HttpServletRequest request) {
        LOGGER.info("Find a profile from token");
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = getToken(header);
        Long userId = tokenProvider.getUserIdFromToken(token);

        ProfileByUserIdResponseDTO responseWrapper = queryService.findByLegacyUser(header, userId);
        return ResponseEntity.ok(getResponseBody(HttpStatus.OK.value(), responseWrapper, request.getRequestURI()));
    }

    @ApiOperation(value = "Get Other Person Profile", response = ResponseEntity.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer <access_token>")
    @GetMapping("/find-profile/{userId}")
    public ResponseEntity<ResponseBody> findProfileCreator(HttpServletRequest request, @PathVariable Long userId) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        ProfileCreatorResponseDTO responseWrapper = queryService.findOtherPersonProfile(header, userId);
        return ResponseEntity.ok(getResponseBody(HttpStatus.OK.value(), responseWrapper, request.getRequestURI()));
    }
}
