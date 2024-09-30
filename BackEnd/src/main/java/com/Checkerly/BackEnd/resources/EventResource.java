package com.Checkerly.BackEnd.resources;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Checkerly.BackEnd.domain.Event;
import com.Checkerly.BackEnd.dto.EventDTO;
import com.Checkerly.BackEnd.services.EventService;
import com.Checkerly.BackEnd.util.URL;

@RestController
@RequestMapping(value="/events")
@CrossOrigin(origins = "http://localhost:5173")
public class EventResource {
    
	@Autowired
	private EventService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Event> findById(@PathVariable String id) {
		Event obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/assuntosearch")
	public ResponseEntity<List<Event>> findByTheme(@RequestParam(defaultValue = "") String text) {
		text = URLDecoder.decode(text, StandardCharsets.UTF_8);
		List<Event> list = service.findByTheme(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/fullsearch")
	public ResponseEntity<List<Event>> fullSearch(
			@RequestParam(defaultValue = "") String text,
			@RequestParam(defaultValue = "") String minDate,
			@RequestParam(defaultValue = "") String maxDate) {

		text = URLDecoder.decode(text, StandardCharsets.UTF_8);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Event> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity <List<EventDTO>> findAll() {
		List<Event> list = service.findAll();
		List<EventDTO> listDto = list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody EventDTO objDto) {
		Event obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<	Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody EventDTO objDto, @PathVariable String id) {
		Event obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
}
