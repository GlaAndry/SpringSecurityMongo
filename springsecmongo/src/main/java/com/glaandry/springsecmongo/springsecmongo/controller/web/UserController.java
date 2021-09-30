package com.glaandry.springsecmongo.springsecmongo.controller.web;

import com.glaandry.springsecmongo.springsecmongo.model.User;
import com.glaandry.springsecmongo.springsecmongo.repository.UserRepository;
import model.MetadataFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("api/admin")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/retrieveFile/{fileName}")
    private MetadataFile findAll(@RequestParam String filename){
        return new MetadataFile();
    }
}
