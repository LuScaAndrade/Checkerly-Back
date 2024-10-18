package com.Checkerly.BackEnd.resources

import com.Checkerly.BackEnd.domain.Organizer
import com.Checkerly.BackEnd.dto.OrganizerDTO
import com.Checkerly.BackEnd.services.OrganizerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.stream.Collectors

@RestController
@RequestMapping(value = "/organizers")
@CrossOrigin(origins = "http://localhost:5173")
class OrganizerResource {
    @Autowired
    private val service: OrganizerService? = null

    @GetMapping
    fun findAll(): ResponseEntity<List<OrganizerDTO?>?>? {
        val list: List<Organizer?>? = service.findAll()
        val listDto: List<OrganizerDTO?>? = list.stream().map({ OrganizerDTO() }).collect(Collectors.toList())
        return ResponseEntity.ok().body(listDto)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String?): ResponseEntity<OrganizerDTO?>? {
        val obj: Organizer? = service.findById(id)
        return ResponseEntity.ok().body(OrganizerDTO(obj))
    }

    @PostMapping
    fun insert(@RequestBody objDto: OrganizerDTO?): ResponseEntity<Void?>? {
        var obj: Organizer? = service.fromDTO(objDto)
        obj = service.insert(obj)
        val uri: URI? =
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri()
        return ResponseEntity.created(uri).build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String?): ResponseEntity<Void?>? {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun update(@RequestBody objDto: OrganizerDTO?, @PathVariable id: String?): ResponseEntity<Void?>? {
        var obj: Organizer? = service.fromDTO(objDto)
        obj.setId(id)
        obj = service.update(obj)
        return ResponseEntity.noContent().build()
    }
}
