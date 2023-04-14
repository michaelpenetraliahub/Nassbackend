package com.jtb.taxpayerws.config;

import com.jtb.taxpayerws.security.JWTAccessDeniedHandler;
import com.jtb.taxpayerws.security.JWTAuthenticationEntryPoint;
import com.jtb.taxpayerws.security.JWTAuthorizationFilter;
import com.jtb.taxpayerws.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final JWTAuthenticationEntryPoint jwtAuthEntryPoint;

    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;

    private final JWTAuthorizationFilter jwtAuthFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JWTAuthenticationEntryPoint jwtAuthEntryPoint, JWTAuthorizationFilter jwtAuthFilter, JWTAccessDeniedHandler jwtAccessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * defines what you want to secure
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                // this disables session creation on Spring Security, we don’t need sessions so this will prevent the creation of session cookies
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth", "/auth/user").permitAll()
                .antMatchers(HttpMethod.POST, "/asset/individual", "/asset/nonindividual").permitAll()
                .antMatchers(HttpMethod.POST, "/tax/individual", "/tax/nonindividual").permitAll()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .antMatchers("/user/password-change").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * defines how you want to secure it
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:9999", "http://127.0.0.1:9999", "http://164.92.157.202:443", "http://164.92.157.202:80", "http://localhost:80", "http://localhost:443","https://localhost","https://tin.nsirs.ng","http://tin.nsirs.ng"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setMaxAge(Long.valueOf(3600));
        configuration.addAllowedHeader("*");

        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
