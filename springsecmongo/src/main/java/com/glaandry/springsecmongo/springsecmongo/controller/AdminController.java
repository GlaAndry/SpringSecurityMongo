package com.glaandry.springsecmongo.springsecmongo.controller;

import com.glaandry.springsecmongo.springsecmongo.model.User;
import com.glaandry.springsecmongo.springsecmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll")
    private List<User> findAll(){
        return userRepository.findAll();
    }
}
