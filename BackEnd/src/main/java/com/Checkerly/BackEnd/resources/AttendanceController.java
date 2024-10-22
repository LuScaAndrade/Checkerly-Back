package com.Checkerly.BackEnd.resources;


import com.Checkerly.BackEnd.dto.AttendanceDTO;
import com.Checkerly.BackEnd.services.AttendanceService;
import com.Checkerly.BackEnd.services.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAttendance(@RequestBody AttendanceDTO request, @AuthenticationPrincipal JwtUserDetails userDetails) {

        try {
            attendanceService.registerAttendance(request, userDetails);
                return ResponseEntity.ok("Presença registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
