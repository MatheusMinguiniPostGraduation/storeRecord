package br.com.minguini.storerecord.controller;

import br.com.minguini.storerecord.form.LoginForm;
import br.com.minguini.storerecord.service.AuthenticationService;
import br.com.minguini.storerecord.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @Autowired
    private AuthenticationService authenticationService;

    //This class does not is pre-set to make independency injection, therefore, we have to configure it manually
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid LoginForm form, UriComponentsBuilder uriBuilder){

        String login = form.getLogin();
        String password = form.getPassword();

        UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(login, password);

        try{
            //The Spring will invoke the Service class that was configured in: SecurityConfigurations.configure
            Authentication authentication = authenticationManager.authenticate(loginData);

            String token = authenticationService.generateToken(authentication);

            return ResponseEntity.badRequest().build();

        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
