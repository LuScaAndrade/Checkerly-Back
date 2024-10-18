package com.Checkerly.BackEnd.resources

import com.Checkerly.BackEnd.domain.User
import com.Checkerly.BackEnd.dto.UserDTO
import com.Checkerly.BackEnd.services.UserService
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
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:5173")
class UserResource {
    @Autowired
    private val service: UserService? = null

    @GetMapping
    fun findAll(): ResponseEntity<List<UserDTO?>?>? {
        val list: List<User?>? = service.findAll()
        val listDto: List<UserDTO?>? = list.stream().map({ UserDTO() }).collect(Collectors.toList())
        return ResponseEntity.ok().body(listDto)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String?): ResponseEntity<UserDTO?>? {
        val obj: User? = service.findById(id)
        return ResponseEntity.ok().body(UserDTO(obj))
    }

    @PostMapping
    fun insert(@RequestBody objDto: UserDTO?): ResponseEntity<Void?>? {
        var obj: User? = service.fromDTO(objDto)
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
    fun update(@RequestBody objDto: UserDTO?, @PathVariable id: String?): ResponseEntity<Void?>? {
        var obj: User? = service.fromDTO(objDto)
        obj.setId(id)
        obj = service.update(obj)
        return ResponseEntity.noContent().build()
    }
}
