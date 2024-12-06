package br.com.library.controller.exception

import br.com.library.dto.exception.ErrorMessageModel
import br.com.library.dto.exception.NullPointerException
import br.com.library.dto.exception.book.BookNotFoundException
import br.com.library.dto.exception.book.reservation.ReservationNotFoundException
import br.com.library.dto.exception.user.UserAlreadyExistsException
import br.com.library.dto.exception.user.UserNotFoundException
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

    @ExceptionHandler(NullPointerException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNullPointerException(ex: NullPointerException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNullPointerException(ex: UserNotFoundException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BookNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleBookNullPointerException(ex: BookNotFoundException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }


    @ExceptionHandler(ReservationNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleReservationNullPointerException(ex: ReservationNotFoundException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }
}