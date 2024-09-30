package com.Checkerly.BackEnd.dto;

import java.io.Serializable;
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
   	private static final long serialVersionUID = 1L; 
    @Id
	private String id;
	private String nomeEvento;
	private String assuntoEvento;
	private String localEvento;
	private Date dataInicio;
	private Date dataFim;
    private Date horaEvento;

    public EventDTO(Event obj) {
		id = obj.getId();
		nomeEvento = obj.getNomeEvento();
		assuntoEvento = obj.getAssuntoEvento();
		dataInicio = obj.getDataInicio();
		dataFim = obj.getDataFim();
        horaEvento = obj.getHoraEvento();
	}

}
