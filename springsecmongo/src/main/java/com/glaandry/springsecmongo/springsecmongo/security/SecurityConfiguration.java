package com.glaandry.springsecmongo.springsecmongo.security;

import com.glaandry.springsecmongo.springsecmongo.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration<conf> extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //Gestione delle autorizzazioni Utente. Si determinano quali sono
    //Gli endpoint da proteggere o meno in base all'autenticazione dell'utente
    //ed in base ai ruoli ricoperti dall'utente stesso.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/authentication/**").permitAll() //everyone
                .antMatchers("/api/prvUser").hasAuthority("ROLE_USER") //Only user
                .antMatchers("/api/prvAdmin", "/api/admin/**").hasAuthority("ROLE_ADMIN") //only admin
                .antMatchers("/api/prvMod").hasAuthority("ROLE_MODERATOR") //only mod
                .anyRequest().authenticated()
                .and()
                .httpBasic();

    }

    //Gestione dell'autenticazione dell'utente tramite la classe
    //UserService.
    //Presenza di PasswordEncoder in quanto viene utilizzato
    //BCrypt per l'inserimento della password criptata all'interno del DB.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder()); //si utilizza l'userService creato appositamente che estende la classe di spring sec.
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
