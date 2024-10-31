package com.Checkerly.BackEnd.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.Checkerly.BackEnd.domain.Organizer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.Checkerly.BackEnd.domain.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    @Query("{ 'assuntoEvento': { $regex: ?0, $options: 'i' } }")
	List<Event> searchAssuntoEvento(String text);
	
	List<Event> findByAssuntoEventoContainingIgnoreCase(String text);
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Event> fullSearch(String text, Date minDate, Date maxDate);

	Optional<Event> findById(String id);
}
