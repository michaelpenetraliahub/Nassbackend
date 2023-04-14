package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.dto.PasswordDto;
import com.jtb.taxpayerws.dto.UserDto;
import com.jtb.taxpayerws.payload.request.ApiRequest;
import com.jtb.taxpayerws.payload.request.EditUserRequest;
import com.jtb.taxpayerws.payload.request.PasswordRequest;
import com.jtb.taxpayerws.payload.request.UserRequest;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.payload.response.PasswordResetResponse;
import com.jtb.taxpayerws.payload.response.UserResponse;
import com.jtb.taxpayerws.service.UserService;
import com.jtb.taxpayerws.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Valid ApiRequest<UserRequest> userRequest) {
        UserDto userDto = modelMapper.map(userRequest.getData(), UserDto.class);
        userDto.setStringRoles(userRequest.getData().getRoles());

        UserResponse response = modelMapper.map(userService.createUser(userDto), UserResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildCreatedResponse(response));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable("id") Long id) {
        UserResponse response = modelMapper.map(userService.getUserDetails(id), UserResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@RequestBody @Valid ApiRequest<EditUserRequest> userRequest, @PathVariable("id") Long id) {
        UserDto userDto = modelMapper.map(userRequest.getData(), UserDto.class);
        userDto.setId(id);
        userDto.setStringRoles(userRequest.getData().getRoles());
        UserResponse response = modelMapper.map(userService.updateUser(userDto), UserResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "password-reset/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PasswordResetResponse>> resetPassword(@PathVariable Long id) {
        UserDto userDto = userService.resetPassword(id);

        PasswordResetResponse response = modelMapper.map(userDto, PasswordResetResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

    @PutMapping(value = "password-change", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserResponse>> changePassword(@RequestBody @Valid ApiRequest<PasswordRequest> passwordRequest) {
        PasswordDto passwordDto = modelMapper.map(passwordRequest.getData(), PasswordDto.class);
        UserResponse response = modelMapper.map(userService.changePassword(passwordDto), UserResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUser() {
        List<UserResponse> users = userService.getAllUsers().stream().map(x -> modelMapper.map(x, UserResponse.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(users));
    }
}
