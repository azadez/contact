package com.example.servingwebcontent.Model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
public class User {
    @Id
    @Column(unique = true)
    private String login;
    private String password;

    protected User() {}

    public User(String login,String password){
        this.login=login;
        this.password=test(password);
    }


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
    public String test(String s) {
        int i = 0;

            String password = s;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            return hashedPassword;


    }
}
