package com.Checkerly.BackEnd.resources.exception

import com.Checkerly.BackEnd.services.exception.ObjectNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice

@ControllerAdvice
class ResourceExceptionHandler {
    fun objectNotFound(e: ObjectNotFoundException, request: HttpServletRequest): ResponseEntity<StandardError?>? {
        val status: HttpStatus? = HttpStatus.NOT_FOUND
        val err: StandardError = StandardError(
            System.currentTimeMillis(),
            status.value(),
            "Não encotrado",
            e.getMessage(),
            request.getRequestURI()
        )
        return ResponseEntity.status(status).body(err)
    }
}
