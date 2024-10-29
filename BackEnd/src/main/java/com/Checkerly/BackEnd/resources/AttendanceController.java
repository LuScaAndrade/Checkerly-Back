package com.Checkerly.BackEnd.resources;


import com.Checkerly.BackEnd.Security.CustomUserDetailsService;
import com.Checkerly.BackEnd.dto.AttendanceDTO;
import com.Checkerly.BackEnd.services.CertificateService;
import com.Checkerly.BackEnd.services.AttendanceService;
import com.Checkerly.BackEnd.services.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private CertificateService certificateService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAttendance(@RequestBody AttendanceDTO request, @AuthenticationPrincipal JwtUserDetails userDetails) {

        try {
            attendanceService.registerAttendance(request, userDetails);
                return ResponseEntity.ok("Presença registrado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/generate-certificate")
    public void registerAttendanceAndSendCertificate(String userEmail, String userName, String eventName) {
        try {
            // Dados para o certificado
            Map<String, String> participantInfo = new HashMap<>();
            participantInfo.put("name", userName);
            participantInfo.put("event", eventName);

            // Gera o PDF do certificado
            byte[] certificatePdf = certificateService.generateCertificate(participantInfo);

            // Envia o certificado por e-mail
            certificateService.sendCertificateByEmail(userEmail, userName, certificatePdf);

            System.out.println("Certificado enviado com sucesso para " + userEmail);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar ou enviar o certificado: " + e.getMessage());
        }
    }
}
