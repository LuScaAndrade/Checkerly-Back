package com.Checkerly.BackEnd.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Checkerly.BackEnd.domain.Event;
import com.Checkerly.BackEnd.dto.EventDTO;
import com.Checkerly.BackEnd.repository.EventRepository;
import com.Checkerly.BackEnd.services.exception.ObjectNotFoundException;

@Service
public class EventService {
    @Autowired
	private EventRepository repo;
	
	public List<Event> findAll(){
		return repo.findAll();
	}

	public Event findById(String id) {
	    Optional<Event> event = repo.findById(id);
	    return event.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Event> findByTheme(String text) {
		return repo.searchAssuntoEvento(text);
	}
	
	public List<Event> fullSearch(String text, java.util.Date min, java.util.Date max) {
		max = new Date(max.getTime() + 24*60*60*1000);
		return repo.fullSearch(text, min, max);
	}

		public Event insert(Event obj) {
		Event savedEvent = repo.save(obj);
		System.out.println("Event inserted: " + savedEvent);
		return savedEvent;
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Event update(Event obj) {
		Event newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Event newObj, Event obj) {
		newObj.setNomeEvento(obj.getNomeEvento());
		newObj.setAssuntoEvento(obj.getAssuntoEvento());
		newObj.setDataInicio(obj.getDataInicio());
		newObj.setDataFim(obj.getDataFim());
		newObj.setHoraEvento(obj.getHoraEvento());
	}

	public Event fromDTO(EventDTO objDto) {
		return new Event(objDto.getId(), objDto.getNomeEvento(),objDto.getAssuntoEvento(), objDto.getLocalEvento(),objDto.getDataInicio(), objDto.getDataFim(), objDto.getHoraEvento());
	}
}
