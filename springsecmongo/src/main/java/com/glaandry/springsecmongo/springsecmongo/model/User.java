package com.glaandry.springsecmongo.springsecmongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "Users")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    @Indexed(unique = true)
    private String email;
    private ArrayList<String> authorities;

    public User(String username, String password, String email, ArrayList<String> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(ArrayList<String> authorities) {
        this.authorities = authorities;
    }
}
