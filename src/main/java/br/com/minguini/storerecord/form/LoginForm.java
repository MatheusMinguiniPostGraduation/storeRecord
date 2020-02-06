package br.com.minguini.storerecord.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginForm {

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
