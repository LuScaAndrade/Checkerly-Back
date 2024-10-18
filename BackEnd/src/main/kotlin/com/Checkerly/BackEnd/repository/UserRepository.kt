package com.Checkerly.BackEnd.repository

import com.Checkerly.BackEnd.domain.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface UserRepository : MongoRepository<User?, String?> {
    fun findByEmail(email: String?): Optional<User?>?
}
