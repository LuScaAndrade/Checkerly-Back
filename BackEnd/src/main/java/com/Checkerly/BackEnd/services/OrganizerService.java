package com.Checkerly.BackEnd.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Checkerly.BackEnd.domain.Organizer;
import com.Checkerly.BackEnd.dto.OrganizerDTO;
import com.Checkerly.BackEnd.repository.OrganizerRepository;
import com.Checkerly.BackEnd.services.exception.ObjectNotFoundException;

@Service
public class OrganizerService {

	@Autowired
    private OrganizerRepository repo;
	
	public List<Organizer> findAll(){
		return repo.findAll();
	}
	
	public Organizer findById(String id) {
	    Optional<Organizer> Organizer = repo.findById(id);
	    return Organizer.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Organizer insert(Organizer obj) {
		Organizer savedOrganizer = repo.save(obj);
		System.out.println("Organizer inserted: " + savedOrganizer);
		return savedOrganizer;
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Organizer update(Organizer obj) {
		Organizer newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Organizer newObj, Organizer obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public Organizer fromDTO(OrganizerDTO objDto) {
		return new Organizer(objDto.getId(), objDto.getName(),objDto.getSenha(), objDto.getEmail(), objDto.getCelular());
	}
}
