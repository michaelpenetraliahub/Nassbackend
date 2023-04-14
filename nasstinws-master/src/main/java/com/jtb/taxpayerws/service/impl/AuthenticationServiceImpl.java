package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.dto.LoginDto;
import com.jtb.taxpayerws.dto.LogoutDto;
import com.jtb.taxpayerws.dto.RefreshTokenDto;
import com.jtb.taxpayerws.entity.RefreshTokenEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.repository.AccessTokenRepository;
import com.jtb.taxpayerws.repository.RefreshTokenRepository;
import com.jtb.taxpayerws.service.AuthenticationService;
import com.jtb.taxpayerws.utils.JwtTokenUtil;
import com.nimbusds.jose.JOSEException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import static com.jtb.taxpayerws.constants.AppConstants.ACCESS_TOKEN;
import static com.jtb.taxpayerws.constants.AppConstants.REFRESH_TOKEN;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtUtils;
    private final AccessTokenRepository accessTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     JwtTokenUtil jwtUtils,
                                     AccessTokenRepository accessTokenRepository,
                                     RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.accessTokenRepository = accessTokenRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public LoginDto login(LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

            Map<String, String> tokens = jwtUtils.generateToken(userDetails);

            loginDto.setAccessToken(tokens.get(ACCESS_TOKEN));
            loginDto.setRefreshToken(tokens.get(REFRESH_TOKEN));
            loginDto.setUserId(userDetails.getId());
            loginDto.setEmail(userDetails.getEmail());
            loginDto.setRoles(userDetails.getAuthoritiesAsStringList());
            loginDto.setFirstname(userDetails.getFirstname());
            loginDto.setLastname(userDetails.getLastname());
            loginDto.setMiddlename(userDetails.getMiddlename());

            return loginDto;
        } catch (DisabledException ex) {
            LOGGER.error(Marker.ANY_NON_NULL_MARKER, ex);
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Please verify your account before you proceed to login");
        } catch (AuthenticationException ex) {
            LOGGER.error(Marker.ANY_NON_NULL_MARKER, ex);
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorInfo.COULD_NOT_VERIFY_CREDENTIALS.getErrorMessage());
        } catch (Exception ex) {
            LOGGER.error(Marker.ANY_NON_NULL_MARKER, ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.INTERNAL_SERVER_ERROR.getErrorMessage());
        }
    }

    @Override
    @Transactional
    public RefreshTokenDto refreshToken(RefreshTokenDto refreshTokenDto) {
        try {
            String refreshToken = refreshTokenDto.getRefreshToken();

            Optional<RefreshTokenEntity> refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken);
            accessTokenRepository.deleteByRefreshToken(refreshTokenEntity.orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, ErrorInfo.UNAUTHORIZED_RESOURCE.getErrorMessage())));
            refreshTokenRepository.deleteByToken(refreshToken);

            UserPrincipal userDetails = (UserPrincipal) jwtUtils.getUserDetailsFromToken(refreshToken);

            Map<String, String> tokens = jwtUtils.generateToken(userDetails);

            refreshTokenDto.setAccessToken(tokens.get(ACCESS_TOKEN));
            refreshTokenDto.setRefreshToken(tokens.get(REFRESH_TOKEN));
            refreshTokenDto.setUserId(userDetails.getId());
            refreshTokenDto.setEmail(userDetails.getEmail());
            refreshTokenDto.setRoles(userDetails.getAuthoritiesAsStringList());

            return refreshTokenDto;
        } catch (AuthenticationException | ParseException | JOSEException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new ApiException(HttpStatus.UNAUTHORIZED, "We could not verify your credentials. Please check and try again.");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.INTERNAL_SERVER_ERROR.getErrorMessage());
        }
    }

    @Override
    @Transactional
    public void logout(LogoutDto logoutDto) {
        try {
            accessTokenRepository.deleteByToken(logoutDto.getAccessToken());
            refreshTokenRepository.deleteByToken(logoutDto.getRefreshToken());

        } catch (AuthenticationException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorInfo.COULD_NOT_VERIFY_CREDENTIALS.getErrorMessage());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.INTERNAL_SERVER_ERROR.getErrorMessage());
        }
    }
}
