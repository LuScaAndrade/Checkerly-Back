package com.Checkerly.BackEnd.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.Checkerly.BackEnd.domain.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EventDTO implements Serializable{
   	@Serial
	private static final long serialVersionUID = 1L;
    @Id
	private String id;
	private String nomeEvento;
	private String assuntoEvento;
	private Double latitude;
	private Double longitude;
	private Date dataInicio;
	private Date dataFim;
    private LocalTime horaEvento;

    public EventDTO(Event obj) {
		id = obj.getId();
		nomeEvento = obj.getNomeEvento();
		assuntoEvento = obj.getAssuntoEvento();
		latitude = obj.getLatitude();
		longitude = obj.getLongitude();
		dataInicio = obj.getDataInicio();
		dataFim = obj.getDataFim();
        horaEvento = obj.getHoraEvento();
	}

}
