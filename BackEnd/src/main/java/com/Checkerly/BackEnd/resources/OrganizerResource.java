package com.Checkerly.BackEnd.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Checkerly.BackEnd.domain.Organizer;
import com.Checkerly.BackEnd.dto.OrganizerDTO;
import com.Checkerly.BackEnd.services.OrganizerService;

@RestController
@RequestMapping(value="/organizers")
public class OrganizerResource {
       	
	@Autowired
	private OrganizerService service;
	
	@GetMapping
	public ResponseEntity <List<OrganizerDTO>> findAll() {
		List<Organizer> list = service.findAll();
		List<OrganizerDTO> listDto = list.stream().map(x -> new OrganizerDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrganizerDTO> findById(@PathVariable String id) {
		Organizer obj = service.findById(id);
		return ResponseEntity.ok().body(new OrganizerDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody OrganizerDTO objDto) {
		Organizer obj = service.fromDTO(objDto);
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
	public ResponseEntity<Void> update(@RequestBody OrganizerDTO objDto, @PathVariable String id) {
		Organizer obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
}
