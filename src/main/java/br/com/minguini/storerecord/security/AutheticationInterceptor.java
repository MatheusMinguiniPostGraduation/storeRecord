package br.com.minguini.storerecord.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutheticationInterceptor extends OncePerRequestFilter {

    // This Interceptor will be executed before going to the controller IN EVERY REQUEST
    // The Spring Security know it because it was previously configured in the SecurityConfigurations.configure
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

       String token = getToken(httpServletRequest);

        System.out.println("Interceptor token: " + token);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }
}
