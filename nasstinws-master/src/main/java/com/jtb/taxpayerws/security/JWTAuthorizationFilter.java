package com.jtb.taxpayerws.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.utils.JwtTokenUtil;
import com.jtb.taxpayerws.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;


@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    public JWTAuthorizationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        ObjectMapper obj = new ObjectMapper();

        Optional<String> token = parseJwt(request);

        if (token.isPresent()) {

            if (!jwtTokenUtil.isValidToken(token.get())) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getOutputStream().println(obj.writeValueAsString(ResponseUtil.buildErrorResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", ErrorInfo.UNAUTHORIZED_RESOURCE.getErrorMessage())));

                return;
            }

            try {
                if(jwtTokenUtil.isTokenExpired(token.get())) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.getOutputStream().println(obj.writeValueAsString(ResponseUtil.buildErrorResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", ErrorInfo.EXPIRED_TOKEN.getErrorMessage())));

                    return;
                }

                UserDetails userDetails = jwtTokenUtil.getUserDetailsFromToken(token.get());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception ex) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getOutputStream().println(obj.writeValueAsString(ResponseUtil.buildErrorResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", ErrorInfo.INVALID_CREDENTIALS.getErrorMessage())));
                return;
            }

        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().println(obj.writeValueAsString(ResponseUtil.buildErrorResponse(HttpStatus.BAD_REQUEST, "BAD REQUEST", ErrorInfo.NO_TOKEN_PROVIDED.getErrorMessage())));
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        Set<String> doNotFilterUrls = new HashSet<>();
        doNotFilterUrls.add("/api/auth");
        doNotFilterUrls.add("/api/asset/individual");
        doNotFilterUrls.add("/api/asset/nonindividual");
        doNotFilterUrls.add("/api/tax/individual");
        doNotFilterUrls.add("/api/tax/nonindividual");

        AntPathMatcher antPathMatcher = new AntPathMatcher();

        return doNotFilterUrls.stream().anyMatch(pattern -> antPathMatcher.match(pattern, request.getRequestURI()));
    }

    private Optional<String> parseJwt(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return Optional.of(authorizationHeader.replace("Bearer", "").trim());
        }

        return Optional.empty();
    }
}
