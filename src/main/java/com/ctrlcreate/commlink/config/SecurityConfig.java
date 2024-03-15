package com.ctrlcreate.commlink.config;


import com.ctrlcreate.commlink.constant.APIConstants;
import com.ctrlcreate.commlink.filter.JWTAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        APIConstants.API_FE_ROOT_URI_1 + APIConstants.URI_SIGNUP,
                        APIConstants.API_FE_ROOT_URI_1 + APIConstants.URI_SIGNIN
//                        APIConstants.API_FE_ROOT_URI_1 + APIConstants.API_URL_APP_VERSION,
//                        APIConstants.API_FE_ROOT_URI_1 + APIConstants.API_URL_VERIFY_OTP
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .disable();

        return http.build();
    }

}