package com.Checkerly.BackEnd.domain

import java.io.Serial

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
class Event : Serializable {
    @Id
    private val id: String? = null
    private val nomeEvento: String? = null
    private val assuntoEvento: String? = null
    private val latitude: String? = null
    private val longitude: String? = null
    private val dataInicio: Date? = null
    private val dataFim: Date? = null
    private val horaEvento: LocalTime? = null

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
        return Objects.equals(id, other.getId())
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 1L
    }
}
