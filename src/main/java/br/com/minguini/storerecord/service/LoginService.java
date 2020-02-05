package br.com.minguini.storerecord.service;

import br.com.minguini.storerecord.entity.User;
import br.com.minguini.storerecord.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    public Boolean isDataRight(String login, String password){
        List<User> users = repository.findByLoginAndPassword(login, password);

        return !users.isEmpty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  repository.findByLogin(username);

        if(user.isPresent()){
            return user.get();
        }

        throw new UsernameNotFoundException("Dados inválidos");
    }
}
