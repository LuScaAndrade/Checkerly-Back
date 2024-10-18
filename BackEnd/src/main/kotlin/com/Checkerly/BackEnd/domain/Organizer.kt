package com.Checkerly.BackEnd.domain

import java.io.Serial

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
class Organizer(
    @field:Id private val id: String?,
    private val name: String?,
    private val senha: String?,
    private val email: String?,
    private val celular: String?
) : Serializable {
    @DBRef(lazy = true)
    private val events: List<Event?> = ArrayList()

    @Override
    fun hashCode(): Int {
        return Objects.hash(id)
    }

    @Override
    fun equals(obj: Object?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (getClass() !== obj.getClass()) return false
        val other: Organizer = obj as Organizer
        return Objects.equals(id, other.getId())
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 1L
    }
}
