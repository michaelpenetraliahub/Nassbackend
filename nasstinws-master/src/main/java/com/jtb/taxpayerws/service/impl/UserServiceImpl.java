package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.constants.AppConstants;
import com.jtb.taxpayerws.dto.EmailDto;
import com.jtb.taxpayerws.dto.PasswordDto;
import com.jtb.taxpayerws.dto.UserDto;
import com.jtb.taxpayerws.entity.RoleEntity;
import com.jtb.taxpayerws.entity.UserEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.repository.RoleRepository;
import com.jtb.taxpayerws.repository.UserRepository;
import com.jtb.taxpayerws.service.UserService;
import com.jtb.taxpayerws.utils.EmailNotificationProxy;
import com.jtb.taxpayerws.utils.FreeMarkerUtil;
import com.jtb.taxpayerws.utils.PasswordGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final FreeMarkerUtil freeMarkerUtil;
    private final EmailNotificationProxy emailNotificationProxy;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, FreeMarkerUtil freeMarkerUtil, EmailNotificationProxy emailNotificationProxy) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.freeMarkerUtil = freeMarkerUtil;
        this.emailNotificationProxy = emailNotificationProxy;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        Optional<UserEntity> optUser = userRepository.findByEmail(userDto.getEmail());
        if(optUser.isPresent()) {
            throw new ApiException(HttpStatus.CONFLICT, "user already exists");
        }

        String password = PasswordGenerator.generateStrongPassword();
        userDto.setPassword(password);

        EmailDto emailDto = buildEmailDto(userDto, "Welcome", AppConstants.SIGNUP_TEMPLATE);

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        RoleEntity roleEntity = roleRepository.findByName(userDto.getStringRoles().get(0)).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "role not found"));
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setRoles(new HashSet<>(Collections.singletonList(roleEntity)));

        userEntity = userRepository.save(userEntity);

        emailNotificationProxy.sendMail(emailDto.getFrom(), emailDto.getAlias(), emailDto.getTo(), emailDto.getSubject(), emailDto.getMessage());


        userDto = modelMapper.map(userEntity, UserDto.class);
        //to return decoded password in response
        userDto.setPassword(password);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserDto oldUserDto = abortIfUserDoesNotExist(userDto.getId());
        modelMapper.map(userDto, oldUserDto);

        UserEntity userEntity = modelMapper.map(oldUserDto, UserEntity.class);
        RoleEntity roleEntity = roleRepository.findByName(oldUserDto.getStringRoles().get(0)).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "role not found"));
        userEntity.setRoles(new HashSet<>(Collections.singletonList(roleEntity)));

        return modelMapper.map(userRepository.save(userEntity), UserDto.class);
    }

    @Override
    public UserDto getUserDetails(Long id) {
        return abortIfUserDoesNotExist(id);
    }

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(x -> modelMapper.map(x, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        abortIfUserDoesNotExist(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDto resetPassword(Long id) {
        UserDto userDto = abortIfUserDoesNotExist(id);
        String newPassword = PasswordGenerator.generateStrongPassword();

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(newPassword));

        UserDto newUserDto = modelMapper.map(userRepository.save(userEntity), UserDto.class);
        newUserDto.setPassword(newPassword);

        EmailDto emailDto = buildEmailDto(newUserDto, "Reset password",  AppConstants.RESET_PASSWORD_TEMPLATE);
        emailNotificationProxy.sendMail(emailDto.getFrom(), emailDto.getAlias(), emailDto.getTo(), emailDto.getSubject(), emailDto.getMessage());

        return newUserDto;
    }

    @Override
    public UserDto changePassword(PasswordDto passwordDto) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!passwordDto.getNewPassword().equals(passwordDto.getConfirmPassword())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorInfo.PASSWORDS_DO_NOT_MATCH.getErrorMessage());
        }

        UserDto userDto = abortIfUserDoesNotExist(principal.getId());
        if (!passwordEncoder.matches(passwordDto.getOldPassword(), userDto.getPassword())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorInfo.INVALID_PASSWORD.getErrorMessage());
        }

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));

        UserDto newUserDto = modelMapper.map(userRepository.save(userEntity), UserDto.class);
        newUserDto.setPassword(passwordDto.getNewPassword());

        return newUserDto;
    }

    private UserDto abortIfUserDoesNotExist(Long id) {
        Optional<UserEntity> optUserEntity = userRepository.findById(id);

        UserEntity userEntity = optUserEntity.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
        return modelMapper.map(userEntity, UserDto.class);
    }

    private EmailDto buildEmailDto(UserDto userDto, String subject, String template) {
        EmailDto emailDto = new EmailDto();

        emailDto.setTo(userDto.getEmail());
        emailDto.setSubject(subject);

        Map<String, Object> templateData = new HashMap<>();
        StringBuilder nameBuilder = new StringBuilder();

        nameBuilder.append(userDto.getFirstname()).append(" ");
        if (userDto.getMiddlename() != null) nameBuilder.append(userDto.getMiddlename()).append(" ");
        nameBuilder.append(userDto.getLastname());

        templateData.put("name", nameBuilder.toString());
        templateData.put("email", userDto.getEmail());
        templateData.put("password", userDto.getPassword());

        emailDto.setMessage(freeMarkerUtil.getContentFromTemplate(templateData, template));

        return emailDto;
    }

}
