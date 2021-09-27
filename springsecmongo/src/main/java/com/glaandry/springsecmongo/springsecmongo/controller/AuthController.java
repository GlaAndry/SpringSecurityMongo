package com.glaandry.springsecmongo.springsecmongo.controller;

import com.glaandry.springsecmongo.springsecmongo.model.AuthenticationRequest;
import com.glaandry.springsecmongo.springsecmongo.model.AuthenticationResponse;
import com.glaandry.springsecmongo.springsecmongo.model.User;
import com.glaandry.springsecmongo.springsecmongo.repository.UserRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController()
@RequestMapping("api/authentication")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/subscribe")
    private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest) {

        String email = authenticationRequest.getEmail();
        String name = authenticationRequest.getUsername();
        String pass = passwordEncoder.encode(authenticationRequest.getPassword());
        //String pass = authenticationRequest.getPassword();
        ArrayList<String> authorities = authenticationRequest.getAuthorities();
        try {
            userRepository.save(new User(name, pass, email, authorities));
        } catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse("Error SUBSCRIPTION for: " + name));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Request OK SUBSCRIPTION for: " + name));
    }

    @PostMapping("/authenticate")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {

        User user = userRepository.findByEmail(authenticationRequest.getEmail());
        String pass = authenticationRequest.getPassword();

        try{
            //email necessaria in quanto nel service abbiamo la funzione load user, la quale esegue una query
            //su db in base all'email.
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), pass));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Errore nell'autenticazione");
        }

        return ResponseEntity.ok(new AuthenticationResponse("Auth OK for: " + user.getEmail()));

    }
}



