package com.Checkerly.BackEnd.services.exception

class ObjectNotFoundException(msg: String?) : RuntimeException(msg) {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
