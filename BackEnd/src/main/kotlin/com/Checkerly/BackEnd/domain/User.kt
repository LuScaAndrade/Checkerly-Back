package com.Checkerly.BackEnd.domain

import java.io.Serial

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
class User : Serializable {
    @Id
    private val id: String? = null
    private val name: String? = null
    private val senha: String? = null
    private val email: String? = null
    private val celular: String? = null

    @Override
    fun hashCode(): Int {
        return Objects.hash(id)
    }

    @Override
    fun equals(obj: Object?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (getClass() !== obj.getClass()) return false
        val other: User = obj as User
        return Objects.equals(id, other.id)
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 1L
    }
}
