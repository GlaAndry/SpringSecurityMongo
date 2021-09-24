package com.glaandry.springsecmongo.springsecmongo.repository;

import com.glaandry.springsecmongo.springsecmongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
