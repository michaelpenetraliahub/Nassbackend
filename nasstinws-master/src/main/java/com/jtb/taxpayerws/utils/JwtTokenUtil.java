package com.jtb.taxpayerws.utils;

import com.jtb.taxpayerws.entity.AccessTokenEntity;
import com.jtb.taxpayerws.entity.RefreshTokenEntity;
import com.jtb.taxpayerws.entity.RoleEntity;
import com.jtb.taxpayerws.entity.UserEntity;
import com.jtb.taxpayerws.repository.AccessTokenRepository;
import com.jtb.taxpayerws.repository.RefreshTokenRepository;
import com.jtb.taxpayerws.service.impl.UserPrincipal;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.jtb.taxpayerws.constants.AppConstants.*;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${security.config.issuer-id}")
    private String issuerId;

    @Value("${security.config.access-token-lifespan}")
    private int accessTokenLifespan;

    @Value("${security.config.refresh-token-lifespan}")
    private int refreshTokenLifespan;

    private final List<String> audience = Arrays.asList("http://localhost:8000");

    TokenEncryptor tokenEncryptionUtil;
    AccessTokenRepository accessTokenRepository;
    RefreshTokenRepository refreshTokenRepository;

    public JwtTokenUtil(@Qualifier("joseTokenEncryptor") TokenEncryptor tokenEncryptionUtil,
                        AccessTokenRepository accessTokenRepository,
                        RefreshTokenRepository refreshTokenRepository) {
        this.tokenEncryptionUtil = tokenEncryptionUtil;
        this.accessTokenRepository = accessTokenRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public Map<String, String> generateToken( UserDetails userDetails) throws Exception {

        Date issueTime = DateUtil.getCurrentSqlDatetime();
        Date accessTokenExpirationTime = DateUtil.addSecondsToDate(accessTokenLifespan);
        Date refreshTokenExpirationTime = DateUtil.addSecondsToDate(refreshTokenLifespan);

        JWTClaimsSet accessTokenClaims = getJwtClaimsSet(userDetails, issueTime, accessTokenExpirationTime);
        JWTClaimsSet refreshTokenClaims = getJwtClaimsSet(userDetails, issueTime, refreshTokenExpirationTime);

        String access_token = tokenEncryptionUtil.encryptToken(accessTokenClaims);
        String refresh_token = tokenEncryptionUtil.encryptToken(refreshTokenClaims);

        Map<String, String> tokens = new HashMap<>();
        tokens.put(ACCESS_TOKEN, access_token);
        tokens.put(REFRESH_TOKEN, refresh_token);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(((UserPrincipal) userDetails).getId());

        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(refresh_token, DateUtil.convertToLocalDateViaInstant(issueTime), DateUtil.convertToLocalDateViaInstant(accessTokenExpirationTime));

        AccessTokenEntity accessTokenEntity = new AccessTokenEntity(access_token, refreshTokenEntity, userEntity, DateUtil.convertToLocalDateViaInstant(issueTime), DateUtil.convertToLocalDateViaInstant(accessTokenExpirationTime));

        refreshTokenRepository.save(refreshTokenEntity);
        accessTokenRepository.save(accessTokenEntity);

        return tokens;
    }

    private JWTClaimsSet getJwtClaimsSet(UserDetails userDetails, Date issueTime, Date accessTokenExpirationTime) {
        return new JWTClaimsSet.Builder()
                .issuer(issuerId)
                .subject(((UserPrincipal) (userDetails)).getId().toString())
                .audience(audience)
                .issueTime(issueTime)
                .expirationTime(accessTokenExpirationTime)
                .claim(ROLES, userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .claim(EMAIL, ((UserPrincipal) (userDetails)).getEmail())
                .jwtID(UUID.randomUUID().toString())
                .build();
    }

    public UserDetails getUserDetailsFromToken(String token) throws Exception {

        EncryptedJWT encryptedJWT = tokenEncryptionUtil.decryptToken(token);

        JWTClaimsSet jwtClaimsSet = encryptedJWT.getJWTClaimsSet();
        Set<RoleEntity> userRoles = jwtClaimsSet.getStringListClaim(ROLES).stream().map(RoleEntity::new).collect(Collectors.toSet());
        String email = jwtClaimsSet.getStringClaim(EMAIL);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(Long.parseLong(jwtClaimsSet.getSubject()));
        userEntity.setEmail(email);
        userEntity.setRoles(userRoles);

        return new UserPrincipal(userEntity);
    }

    public Boolean isValidToken(String token) {

        if(!accessTokenRepository.existsByToken(token))
            return refreshTokenRepository.existsByToken(token);
        else{
            return true;
        }
    }

    public  boolean isTokenExpired(String token) throws Exception {
        EncryptedJWT encryptedJWT = tokenEncryptionUtil.decryptToken(token);

        LocalDateTime expirationTime = DateUtil.convertToLocalDateTime(encryptedJWT.getJWTClaimsSet().getExpirationTime());
        LocalDateTime currentTime = DateUtil.getCurrentSqlDatetime().toLocalDateTime();

        return currentTime.isAfter(expirationTime);
    }

}
