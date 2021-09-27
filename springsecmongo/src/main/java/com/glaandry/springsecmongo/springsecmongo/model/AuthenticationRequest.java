package com.glaandry.springsecmongo.springsecmongo.model;

import java.util.ArrayList;

public class AuthenticationRequest {

    private String email;
    private String password;
    private String username;
    private ArrayList<String> authorities;

    public AuthenticationRequest() {
    }

    public ArrayList<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(ArrayList<String> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
