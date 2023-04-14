package com.jtb.taxpayerws.service.impl;


import com.jtb.taxpayerws.entity.UserEntity;
import com.jtb.taxpayerws.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);

        return optionalUserEntity.map(UserPrincipal::new).orElseThrow(() -> new UsernameNotFoundException("invalid username or password"));
    }
}
