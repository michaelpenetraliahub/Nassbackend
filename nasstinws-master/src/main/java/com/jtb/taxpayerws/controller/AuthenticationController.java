package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.dto.LoginDto;
import com.jtb.taxpayerws.dto.LogoutDto;
import com.jtb.taxpayerws.dto.RefreshTokenDto;
import com.jtb.taxpayerws.payload.request.ApiRequest;
import com.jtb.taxpayerws.payload.request.LoginRequest;
import com.jtb.taxpayerws.payload.request.LogoutRequest;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.payload.response.LoginResponse;
import com.jtb.taxpayerws.payload.response.RefreshTokenResponse;
import com.jtb.taxpayerws.service.AuthenticationService;
import com.jtb.taxpayerws.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final ModelMapper modelMapper;
    private final AuthenticationService authenticationService;

    public AuthenticationController(ModelMapper modelMapper, AuthenticationService authenticationService) {
        this.modelMapper = modelMapper;
        this.authenticationService = authenticationService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid ApiRequest<LoginRequest> loginRequest) {
        LoginDto loginDto = modelMapper.map(loginRequest.getData(), LoginDto.class);
        loginDto = authenticationService.login(loginDto);

        LoginResponse response = modelMapper.map(loginDto, LoginResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logout(@RequestBody @Valid ApiRequest<LogoutRequest> logoutRequest, HttpServletRequest request) {

        String encryptedAccessToken = request.getHeader("Authorization").split(" ")[1];
        LogoutDto logoutDto = new LogoutDto(encryptedAccessToken, logoutRequest.getData().getRefreshToken());
        authenticationService.logout(logoutDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/refreshtoken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> refreshToken(HttpServletRequest request) {

        String encryptedRefreshToken = request.getHeader("Authorization").split(" ")[1];
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto(encryptedRefreshToken);
        refreshTokenDto = authenticationService.refreshToken(refreshTokenDto);

        RefreshTokenResponse response = modelMapper.map(refreshTokenDto, RefreshTokenResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }
}
