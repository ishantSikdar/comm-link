package com.ctrlcreate.commlink.filter;

import com.ctrlcreate.commlink.service.ErrorResponseService;
import com.ctrlcreate.commlink.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



@Component
@Slf4j
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {


    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {


        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String JWTToken;
        final String userEmail;


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            ErrorResponseService.sendNoAuthHeaderResponse(response);
            return;
        }

        try {
            JWTToken = authHeader.substring("Bearer".length());
            userEmail = jwtService.extractEmail(JWTToken);

        } catch (Exception e) {
            // Token is invalid, or some error occurred in retrieving claims
            ErrorResponseService.sendInvalidTokenFailedResponse(response);
            return;
        }

        try {
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (Boolean.TRUE.equals(jwtService.isTokenValid(JWTToken, userDetails))) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    ErrorResponseService.sendInvalidTokenFailedResponse(response);
                    return;
                }
            }

            // Allow the request to proceed to the interceptor regardless of token validity
            filterChain.doFilter(request, response);

        } catch (UsernameNotFoundException ex){
            ErrorResponseService.sendInvalidTokenFailedResponse(response);
            return;
        }
    }
}

