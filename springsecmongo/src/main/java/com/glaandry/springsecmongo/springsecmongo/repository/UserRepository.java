package com.glaandry.springsecmongo.springsecmongo.repository;

import com.glaandry.springsecmongo.springsecmongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
    User findFirstByEmail(String email);
}
