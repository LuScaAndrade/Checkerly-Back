package com.Checkerly.BackEnd.resources

import com.Checkerly.BackEnd.domain.Event
import com.Checkerly.BackEnd.dto.EventDTO
import com.Checkerly.BackEnd.services.EventService
import com.Checkerly.BackEnd.util.URL
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.Date
import java.util.stream.Collectors

@RestController
@RequestMapping(value = "/events")
@CrossOrigin(origins = "http://localhost:5173")
class EventResource {
    @Autowired
    private val service: EventService? = null

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String?): ResponseEntity<Event?>? {
        val obj: Event? = service.findById(id)
        return ResponseEntity.ok().body(obj)
    }

    @GetMapping("/assuntosearch")
    fun findByTheme(@RequestParam(defaultValue = "") text: String?): ResponseEntity<List<Event?>?>? {
        var text: String? = text
        text = URLDecoder.decode(text, StandardCharsets.UTF_8)
        val list: List<Event?>? = service.findByTheme(text)
        return ResponseEntity.ok().body(list)
    }

    @GetMapping("/fullsearch")
    fun fullSearch(
        @RequestParam(defaultValue = "") text: String?,
        @RequestParam(defaultValue = "") minDate: String?,
        @RequestParam(defaultValue = "") maxDate: String?
    ): ResponseEntity<List<Event?>?>? {
        var text: String? = text
        text = URLDecoder.decode(text, StandardCharsets.UTF_8)
        val min: Date? = URL.convertDate(minDate, Date(0L))
        val max: Date? = URL.convertDate(maxDate, Date())
        val list: List<Event?>? = service.fullSearch(text, min, max)
        return ResponseEntity.ok().body(list)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<EventDTO?>?>? {
        val list: List<Event?>? = service.findAll()
        val listDto: List<EventDTO?>? = list.stream().map({ EventDTO() }).collect(Collectors.toList())
        return ResponseEntity.ok().body(listDto)
    }

    @PostMapping
    fun insert(@RequestBody objDto: EventDTO?): ResponseEntity<Void?>? {
        var obj: Event? = service.fromDTO(objDto)
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
    fun update(@RequestBody objDto: EventDTO?, @PathVariable id: String?): ResponseEntity<Void?>? {
        var obj: Event? = service.fromDTO(objDto)
        obj.setId(id)
        obj = service.update(obj)
        return ResponseEntity.noContent().build()
    }
}
