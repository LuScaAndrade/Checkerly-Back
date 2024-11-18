package com.Checkerly.BackEnd.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.Checkerly.BackEnd.dto.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Event implements Serializable{
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

	@DBRef(lazy = true)
	private List<User> participantes = new ArrayList<>();

	public Event(String id, String nomeEvento, String assuntoEvento,
				 double latitude, double longitude, Date dataInicio, Date dataFim, LocalTime horaEvento) {
		this.id = id;
		this.nomeEvento = nomeEvento;
		this.assuntoEvento = assuntoEvento;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.horaEvento = horaEvento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(id, other.getId());
	}
}
