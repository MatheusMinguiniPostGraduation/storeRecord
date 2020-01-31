package br.com.minguini.storerecord.controller;

import br.com.minguini.storerecord.form.LoginForm;
import br.com.minguini.storerecord.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService service;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginForm form, UriComponentsBuilder uriBuilder){

        String login = form.getLogin();
        String password = form.getPassword();

        if(service.isDataRight(login, password)){
            return ResponseEntity.ok("CHAVE_ACESSO");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
