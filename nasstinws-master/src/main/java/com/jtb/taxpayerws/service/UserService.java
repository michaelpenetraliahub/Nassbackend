package com.jtb.taxpayerws.service;

import com.jtb.taxpayerws.dto.PasswordDto;
import com.jtb.taxpayerws.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto getUserDetails(Long id);

    List<UserDto> getAllUsers();

    void deleteUser(Long id);

    UserDto resetPassword(Long id);

    UserDto changePassword(PasswordDto passwordDto);
}
