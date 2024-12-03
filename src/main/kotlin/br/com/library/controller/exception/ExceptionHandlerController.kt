package br.com.library.controller.exception

import br.com.library.dto.exception.ErrorMessageModel
import br.com.library.dto.exception.UserAlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(UserAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleUserAlreadyExistsException(ex: UserAlreadyExistsException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.CONFLICT.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.CONFLICT)
    }
}