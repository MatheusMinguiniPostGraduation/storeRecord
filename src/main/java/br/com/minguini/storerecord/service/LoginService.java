package br.com.minguini.storerecord.service;

import br.com.minguini.storerecord.entity.User;
import br.com.minguini.storerecord.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserRepository repository;

    public Boolean isDataRight(String login, String password){
        List<User> users = repository.findByLoginAndPassword(login, password);

        return !users.isEmpty();
    }

}
