package br.com.minguini.storerecord.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

////Enabling Security by Spring Security features
@EnableWebSecurity

////Telling Spring that it is a configuration class
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll();
    }

    //    //To configure the authentication configuration (Login, password)
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//    }
//
//    //To configure the authorization (Which profile can access each URI)
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        //Enabling access to my public End Points (No authentication needed)
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/login").permitAll();
//
//    }
//
//    //To configure static resources (Images, for instance)
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//
//    }
}
