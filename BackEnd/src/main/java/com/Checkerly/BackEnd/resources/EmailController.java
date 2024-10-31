package com.Checkerly.BackEnd.resources;

import com.Checkerly.BackEnd.dto.EmailDTO;
import com.Checkerly.BackEnd.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sendEmail(@RequestBody EmailDTO emailDTO, @RequestParam String name,
                          @RequestParam String eventName) {
        emailService.sendEmail(emailDTO, name, eventName);
    }
}
