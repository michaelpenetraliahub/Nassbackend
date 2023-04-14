package com.jtb.taxpayerws.service;

import com.jtb.taxpayerws.dto.LoginDto;
import com.jtb.taxpayerws.dto.LogoutDto;
import com.jtb.taxpayerws.dto.RefreshTokenDto;

public interface AuthenticationService {
    LoginDto login(LoginDto loginDto);
    RefreshTokenDto refreshToken(RefreshTokenDto refreshTokenDto);
    void logout(LogoutDto logoutDto);
}
