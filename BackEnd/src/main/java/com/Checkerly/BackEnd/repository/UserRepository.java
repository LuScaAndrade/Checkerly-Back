package com.Checkerly.BackEnd.repository;

import com.Checkerly.BackEnd.domain.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findByEmail(String email);
}
