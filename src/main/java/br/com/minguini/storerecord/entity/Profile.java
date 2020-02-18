package br.com.minguini.storerecord.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "profiles")
    private List<User> User;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<br.com.minguini.storerecord.entity.User> getUser() {
        return User;
    }

    public void setUser(List<br.com.minguini.storerecord.entity.User> user) {
        User = user;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
