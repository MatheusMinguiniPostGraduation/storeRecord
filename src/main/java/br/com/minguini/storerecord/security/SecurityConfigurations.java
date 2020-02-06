package br.com.minguini.storerecord.security;

import br.com.minguini.storerecord.repository.UserRepository;
import br.com.minguini.storerecord.service.AuthenticationService;
import br.com.minguini.storerecord.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Enabling Security by Spring Security features
@EnableWebSecurity

//Telling Spring that it is a configuration class
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    //To configure the authentication configuration (Login, password)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());
    }

//    public static void main(String[] args) {
//        String password = new BCryptPasswordEncoder().encode("teste");
//        System.out.println(password);
//    }

    //To configure the authorization (Which profile can access each URI)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //Enabling access to my public End Points (No authentication needed)
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                // CSRF is a sort of hacking attack.
                // Because the application is authenticating the user by its token.
                // There is no necessity to handle that attack. That is why CSRF is disabled
                .and().csrf().disable()
                //Telling Spring Security that the authentiction will no longer use session, it will use token otherwise
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //Adding the filter that will be executed before every request
                .addFilterBefore(new AuthenticationInterceptor(authenticationService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }




}
