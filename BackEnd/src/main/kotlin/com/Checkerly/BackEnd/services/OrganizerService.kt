package com.Checkerly.BackEnd.services

import com.Checkerly.BackEnd.domain.Organizer
import com.Checkerly.BackEnd.dto.OrganizerDTO
import com.Checkerly.BackEnd.repository.OrganizerRepository
import com.Checkerly.BackEnd.services.exception.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class OrganizerService {
    @Autowired
    private val repo: OrganizerRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    fun findAll(): List<Organizer?>? {
        return repo.findAll()
    }

    fun findById(id: String?): Organizer? {
        val Organizer: Optional<Organizer?>? = repo.findById(id)
        return Organizer.orElseThrow({ ObjectNotFoundException("Objeto não encontrado") })
    }

    fun insert(obj: Organizer): Organizer? {
        val encodedPassword: String? = passwordEncoder.encode(obj.getSenha())
        obj.setSenha(encodedPassword)
        return repo.save(obj)
    }

    fun delete(id: String?) {
        findById(id)
        repo.deleteById(id)
    }

    fun update(obj: Organizer): Organizer? {
        val newObj: Organizer? = findById(obj.getId())
        updateData(newObj, obj)
        return repo.save(newObj)
    }

    private fun updateData(newObj: Organizer, obj: Organizer) {
        newObj.setName(obj.getName())
        newObj.setEmail(obj.getEmail())
    }

    fun fromDTO(objDto: OrganizerDTO): Organizer? {
        return Organizer(objDto.getId(), objDto.getName(), objDto.getSenha(), objDto.getEmail(), objDto.getCelular())
    }
}
