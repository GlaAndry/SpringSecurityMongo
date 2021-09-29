package com.glaandry.springsecmongo.springsecmongo.controller;

import com.glaandry.springsecmongo.springsecmongo.common.Constants;
import com.glaandry.springsecmongo.springsecmongo.model.http.AuthenticationRequest;
import com.glaandry.springsecmongo.springsecmongo.model.User;
import com.glaandry.springsecmongo.springsecmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<String> authorities = authenticationRequest.getAuthorities();

        if (email == null || name == null || pass == null || authorities == null)
            return new ResponseEntity<>("Parametri di iscrizione incompleti ", HttpStatus.BAD_REQUEST);


        try {

            if (userRepository.findFirstByEmail(email) == null) {
                if (authorities.isEmpty()) {
                    //Senza ruoli viene creato un utente con accesso ROLE_USER
                    userRepository.save(new User(name, pass, email, new ArrayList<String>(Collections.singleton(Constants.DEFAULT_ROLE)), true));
                } else {
                    userRepository.save(new User(name, pass, email, authorities, true));
                }
            } else {
                return new ResponseEntity<>("Email utilizzata da un altro utente.", HttpStatus.resolve(400));
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Errore nell'iscrizione: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Iscrizione effettuata correttamente", HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {

        User user = userRepository.findByEmail(authenticationRequest.getEmail());
        String pass = authenticationRequest.getPassword();

        try {
            //email necessaria in quanto nel service abbiamo la funzione load user, la quale esegue una query
            //su db in base all'email.
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), pass));
        } catch (Exception e) {
            return new ResponseEntity<>("Errore nell'autenticazione: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Autenticazione effettuata correttamente", HttpStatus.OK);
    }
}



