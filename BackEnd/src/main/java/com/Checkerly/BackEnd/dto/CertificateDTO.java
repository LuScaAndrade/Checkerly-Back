package com.Checkerly.BackEnd.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDTO {
    private String participantName;
    private String eventName;
    private LocalDate eventDate;
    private String filePath;
}
