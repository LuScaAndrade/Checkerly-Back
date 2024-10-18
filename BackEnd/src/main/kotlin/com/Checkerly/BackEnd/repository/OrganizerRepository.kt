package com.Checkerly.BackEnd.repository

import com.Checkerly.BackEnd.domain.Organizer
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface OrganizerRepository : MongoRepository<Organizer?, String?> {
    fun findByEmail(email: String?): Optional<Organizer?>?
}
