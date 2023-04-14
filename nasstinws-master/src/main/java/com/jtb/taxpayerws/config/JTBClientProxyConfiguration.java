package com.jtb.taxpayerws.config;

import com.jtb.taxpayerws.exception.CustomErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JTBClientProxyConfiguration {

    @Bean
    public OkHttpClient client(){
        return new OkHttpClient();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
