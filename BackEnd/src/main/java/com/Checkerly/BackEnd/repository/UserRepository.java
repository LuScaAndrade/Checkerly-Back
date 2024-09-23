package com.Checkerly.BackEnd.repository;

import com.Checkerly.BackEnd.domain.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
}
