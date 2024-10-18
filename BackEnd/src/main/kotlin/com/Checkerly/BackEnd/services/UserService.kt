package com.Checkerly.BackEnd.services

import com.Checkerly.BackEnd.domain.User
import com.Checkerly.BackEnd.dto.UserDTO
import com.Checkerly.BackEnd.repository.UserRepository
import com.Checkerly.BackEnd.services.exception.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService {
    @Autowired
    private val repo: UserRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    fun findAll(): List<User?>? {
        return repo.findAll()
    }

    fun findById(id: String?): User? {
        val user: Optional<User?>? = repo.findById(id)
        return user.orElseThrow({ ObjectNotFoundException("Objeto não encontrado") })
    }

    fun insert(obj: User): User? {
        val encodedPassword: String? = passwordEncoder.encode(obj.getSenha())
        obj.setSenha(encodedPassword)
        return repo.save(obj)
    }

    fun delete(id: String?) {
        findById(id)
        repo.deleteById(id)
    }

    fun update(obj: User): User? {
        val newObj: User? = findById(obj.getId())
        updateData(newObj, obj)
        return repo.save(newObj)
    }

    private fun updateData(newObj: User, obj: User) {
        newObj.setName(obj.getName())
        newObj.setEmail(obj.getEmail())
    }

    fun fromDTO(objDto: UserDTO): User? {
        return User(objDto.getId(), objDto.getName(), objDto.getSenha(), objDto.getEmail(), objDto.getCelular())
    }
}
