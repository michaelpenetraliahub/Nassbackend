package com.jtb.taxpayerws.utils;

import org.springframework.stereotype.Component;

@Component
public interface TokenEncryptor {
    <T> String encryptToken(T token) throws Exception;
    <T> T decryptToken(String token) throws Exception;
}
