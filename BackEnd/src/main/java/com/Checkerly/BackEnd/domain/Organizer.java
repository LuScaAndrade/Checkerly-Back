package com.Checkerly.BackEnd.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

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

public class Organizer implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String senha;
	private String email;
	private String celular;

	@DBRef(lazy = true)
	private List<Event> events = new ArrayList<>();

	public Organizer(String id, String name, String senha, String email, String celular) {
		super();
		this.id = id;
		this.name = name;
		this.senha = senha;
		this.email = email;
		this.celular = celular;
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
		Organizer other = (Organizer) obj;
		return Objects.equals(id, other.getId());
	}
}
