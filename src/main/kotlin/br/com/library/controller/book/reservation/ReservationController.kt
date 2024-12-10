package br.com.library.controller.book.reservation

import br.com.library.dto.book.reservation.request.ReservationRequest
import br.com.library.dto.book.reservation.request.ReservationStatusUpdateRequest
import br.com.library.dto.book.reservation.response.ReservationResponse
import br.com.library.service.book.reservation.ReservationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("library-api/reservation")
class ReservationController (
    private val reservationService: ReservationService
){
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody reservationRequest: ReservationRequest
    ): ReservationResponse = reservationService.createReservation(reservationRequest)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listAllActive (
    ): List<ReservationResponse> = reservationService.findAllReservation()

    @GetMapping("user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun listUserWithReservation (
        @PathVariable userId: Int
    ): List<ReservationResponse> = reservationService.findUserIdWithReservation(userId)


    @GetMapping("book/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    fun listBookWithReservation (
        @PathVariable bookId: Int
    ): List<ReservationResponse> = reservationService.findBookIdWithReservation(bookId)

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateStatus(
        @PathVariable id: Int,
        @RequestBody requestUpdate: ReservationStatusUpdateRequest
    ): ReservationResponse = reservationService.updateStatusReservation(id, requestUpdate)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun disable(
        @PathVariable id: Int,
    ) = reservationService.disableReservation(id)

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(
        @PathVariable id: Int
    ): ReservationResponse = reservationService.findById(id)

}