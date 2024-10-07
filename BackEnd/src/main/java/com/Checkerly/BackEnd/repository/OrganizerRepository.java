package com.Checkerly.BackEnd.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Checkerly.BackEnd.domain.Organizer;

public interface OrganizerRepository extends MongoRepository<Organizer, String>{
     Optional<Organizer> findByEmail(String email);
}
