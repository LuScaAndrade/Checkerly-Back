package com.Checkerly.BackEnd.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Checkerly.BackEnd.domain.Organizer;

public interface OrganizerRepository extends MongoRepository<Organizer, String>{

}
