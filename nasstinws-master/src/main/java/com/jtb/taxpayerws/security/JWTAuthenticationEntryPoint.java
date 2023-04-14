package com.jtb.taxpayerws.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ErrorDetails;
import com.jtb.taxpayerws.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ObjectMapper obj = new ObjectMapper();
        response.getOutputStream().println(obj.writeValueAsString(ResponseUtil.buildErrorResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", ErrorInfo.UNAUTHORIZED_RESOURCE.getErrorMessage())));
    }
}
