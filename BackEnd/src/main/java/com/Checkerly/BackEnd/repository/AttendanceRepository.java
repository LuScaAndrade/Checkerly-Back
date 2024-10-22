package com.Checkerly.BackEnd.repository;

import com.Checkerly.BackEnd.domain.Attendance;
import com.Checkerly.BackEnd.domain.Organizer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AttendanceRepository extends MongoRepository<Attendance, String> {
    boolean existsByUserIdAndEventId(String id, String id1);
}
