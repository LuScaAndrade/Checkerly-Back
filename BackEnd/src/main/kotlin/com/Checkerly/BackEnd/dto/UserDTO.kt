package com.Checkerly.BackEnd.dto

import com.Checkerly.BackEnd.domain.User
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.io.Serial
import java.io.Serializable

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class UserDTO(obj: User) : Serializable {
    private val id: String?
    private val name: String?
    private val senha: String?
    private val email: String?
    private val celular: String?

    init {
        id = obj.getId()
        name = obj.getName()
        senha = obj.getSenha()
        email = obj.getEmail()
        celular = obj.getCelular()
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 1L
    }
}