package com.capstoneproject.ms3authenticationservicev1.authentication.filter;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.capstoneproject.ms3authenticationservicev1.authentication.manager.TokenManager;
import com.capstoneproject.ms3authenticationservicev1.authentication.service.CustomUserDetailsService;
import com.capstoneproject.ms3authenticationservicev1.user.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private TokenManager tokenManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException ,IllegalArgumentException{

        try {
            if (!request.getRequestURI().equals("/user/v1/login")){
                String tokenHeader = request.getHeader("Authorization");
                String username = null;
                String token = null;
                if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                    token = tokenHeader.substring(7);
                    username = tokenManager.getUsernameFromToken(token);
                } else {
                    throw new AuthenticationException("Bearer String not found in token");
                }
                if (null != username &&SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (Boolean.TRUE.equals(tokenManager.validateJwtToken(token, userDetails))) {
                        UsernamePasswordAuthenticationToken
                                authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null,
                                userDetails.getAuthorities());
                        authenticationToken.setDetails(new
                                WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }

            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
        filterChain.doFilter(request, response);

    }
}
