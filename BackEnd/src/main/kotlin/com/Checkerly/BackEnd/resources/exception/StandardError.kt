package com.Checkerly.BackEnd.resources.exception

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.io.Serializable

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class StandardError : Serializable {
    private val timestamp: Long? = null
    private val status: Integer? = null
    private val error: String? = null
    private val message: String? = null
    private val path: String? = null

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
