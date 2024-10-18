package com.Checkerly.BackEnd.repository

import com.Checkerly.BackEnd.domain.Event
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface EventRepository : MongoRepository<Event?, String?> {
    @Query("{ 'assuntoEvento': { \$regex: ?0, \$options: 'i' } }")
    fun searchAssuntoEvento(text: String?): List<Event?>?

    fun findByAssuntoEventoContainingIgnoreCase(text: String?): List<Event?>?

    @Query("{ \$and: [ { date: {\$gte: ?1} }, { date: { \$lte: ?2} } , { \$or: [ { 'title': { \$regex: ?0, \$options: 'i' } }, { 'body': { \$regex: ?0, \$options: 'i' } }, { 'comments.text': { \$regex: ?0, \$options: 'i' } } ] } ] }")
    fun fullSearch(text: String?, minDate: Date?, maxDate: Date?): List<Event?>?
}
