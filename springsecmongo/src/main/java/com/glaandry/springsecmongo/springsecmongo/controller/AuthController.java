package com.glaandry.springsecmongo.springsecmongo.controller;

import com.glaandry.springsecmongo.springsecmongo.model.AuthenticationRequest;
import com.glaandry.springsecmongo.springsecmongo.model.AuthenticationResponse;
import com.glaandry.springsecmongo.springsecmongo.model.User;
import com.glaandry.springsecmongo.springsecmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/subs")
    private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest){

        String user = authenticationRequest.getUsername();
        String pass = authenticationRequest.getPassword();
        //ArrayList<String> authorities = userRepository.findByUsername(user).getAuthorities();
        try{
            userRepository.save(new User(user, pass));
        } catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error SUBSCRIPTION for: " + user));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Request OK SUBSCRIPTION for: " + user));
    }

    @PostMapping("/auth")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest){

        String user = authenticationRequest.getUsername();
        String pass = authenticationRequest.getPassword();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, pass));
        } catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Auth Error for: " + user));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Auth OK for: " + user));
    }

}
