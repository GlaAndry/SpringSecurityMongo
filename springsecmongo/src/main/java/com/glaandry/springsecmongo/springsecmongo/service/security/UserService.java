package com.glaandry.springsecmongo.springsecmongo.service.security;

import com.glaandry.springsecmongo.springsecmongo.model.User;
import com.glaandry.springsecmongo.springsecmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    //override della service per avere una autenticazione personalizzata all'interno
    //dell'applicazione
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if(user == null) throw new UsernameNotFoundException("Utente non trovato");
        return new UserDetailsImpl(user);
    }

}
