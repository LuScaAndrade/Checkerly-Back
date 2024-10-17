package com.Checkerly.BackEnd.dto;

import java.io.Serial;
import java.io.Serializable;

import com.Checkerly.BackEnd.domain.Organizer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrganizerDTO implements Serializable{
    @Serial
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String senha;
	private String email;
	private String celular;
	
	public OrganizerDTO(Organizer obj) {
		id = obj.getId();
		name = obj.getName();
		senha = obj.getSenha();
		email = obj.getEmail();
		celular = obj.getCelular();
	}
}
