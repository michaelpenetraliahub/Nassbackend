package com.jtb.taxpayerws.exception.event;

import com.jtb.taxpayerws.entity.RoleEntity;
import com.jtb.taxpayerws.entity.UserEntity;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.repository.RoleRepository;
import com.jtb.taxpayerws.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ApplicationStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationStartupEvent(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    @Transactional
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        List<String> defaultRoles = Arrays.asList("ADMIN", "STAFF", "OBSERVER");
        String defaultEmail = "admin@admin.com";

        List<RoleEntity> roleEntities = roleRepository.findAll();
        if (roleEntities.isEmpty()) {
            Set<RoleEntity> roleEntitySet = defaultRoles.stream().map(RoleEntity::new).collect(Collectors.toSet());
            roleRepository.saveAll(roleEntitySet);
        }

        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(defaultEmail);
        if (!optionalUserEntity.isPresent()) {
            createDefaultUser(defaultEmail);
        }
    }

    private void createDefaultUser(String defaultEmail) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname("Admin");
        userEntity.setLastname("Taxpayer");
        userEntity.setEmail(defaultEmail);
        userEntity.setPhone("08123456789");
        userEntity.setPassword(passwordEncoder.encode("password"));
        userEntity.setEnabled(true);
        RoleEntity roleEntity = roleRepository.findByName("ADMIN").orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "role not found"));
        userEntity.setRoles(new HashSet<>(Collections.singletonList(roleEntity)));

        userRepository.save(userEntity);
    }
}
