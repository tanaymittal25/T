package org.srm.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.srm.blog.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
