package com.glaandry.springsecmongo.springsecmongo;

import com.glaandry.springsecmongo.springsecmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringsecmongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecmongoApplication.class, args);
    }
}
