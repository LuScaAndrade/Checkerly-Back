package com.Checkerly.BackEnd.services;

import com.Checkerly.BackEnd.domain.Attendance;
import com.Checkerly.BackEnd.domain.Event;
import com.Checkerly.BackEnd.dto.AttendanceDTO;
import com.Checkerly.BackEnd.repository.AttendanceRepository;
import com.Checkerly.BackEnd.repository.EventRepository;
import com.Checkerly.BackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.StreamSupport;

@Service
public class AttendanceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void registerAttendance(AttendanceDTO request, JwtUserDetails userDetails) {
        // Validar se o evento existe
       Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));

        // Validar localização (exemplo de validação simples)
        if (!event.getLocation().equals(request.getLocation())) {
            throw new IllegalArgumentException("Localização incorreta");
        }

        // Verificar se o usuário já registrou presença no evento
        boolean alreadyPresent = attendanceRepository.existsByUserIdAndEventId(userDetails.getId(), event.getId());
        if (alreadyPresent) {
            throw new IllegalArgumentException("Presença já registrada para este evento");
        }

        // Registrar a presença
        Attendance attendance = new Attendance();
        attendance.setUserId(userDetails.getId());
        attendance.setEventId(event.getId());
        attendance.setTimestamp(LocalDateTime.now());
        attendance.setCertificateIssued(false);
        attendanceRepository.save(attendance);
    }
}
