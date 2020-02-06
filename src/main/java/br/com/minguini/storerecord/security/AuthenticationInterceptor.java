package br.com.minguini.storerecord.security;

import br.com.minguini.storerecord.entity.User;
import br.com.minguini.storerecord.repository.UserRepository;
import br.com.minguini.storerecord.service.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationInterceptor extends OncePerRequestFilter {

    private AuthenticationService authenticationService;

    private UserRepository userRepository;

    public AuthenticationInterceptor(AuthenticationService authenticationService, UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    // This Interceptor will be executed before going to the controller IN EVERY REQUEST
    // The Spring Security know it because it was previously configured in the SecurityConfigurations.configure
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

       String token = getToken(httpServletRequest);

       if(this.authenticationService.isTokenValid(token)){
           authenticateClient(token);
       }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    //No need to validate user and password again since I have got a valid token, therefore, here, I just tell the Spring to authenticate the user
    private void authenticateClient(String token) {
        User user = getUserByToken(token);

        if(user != null){
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private User getUserByToken(String token){
        Long userId = this.authenticationService.getUserId(token);
        return  this.userRepository.findById(userId).get();
    }

    private String getToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }
}
