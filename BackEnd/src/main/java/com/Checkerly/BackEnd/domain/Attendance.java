package com.Checkerly.BackEnd.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document

public class Attendance {
    @Id
    private String id;
    private String userId;
    private String eventId;
    private LocalDateTime timestamp;
    private boolean certificateIssued;
}
