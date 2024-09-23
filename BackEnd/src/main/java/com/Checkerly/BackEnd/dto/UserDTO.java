package com.Checkerly.BackEnd.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO implements Serializable{
    private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
}
