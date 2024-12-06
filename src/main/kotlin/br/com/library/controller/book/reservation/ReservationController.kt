package br.com.library.controller.book.reservation

import br.com.library.dto.book.reservation.request.ReservationRequest
import br.com.library.dto.book.reservation.request.ReservationStatusUpdateRequest
import br.com.library.dto.book.reservation.response.ReservationResponse
import br.com.library.service.book.reservation.ReservationService
import org.springframework.http.HttpStatus
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
    fun listAll (
    ): List<ReservationResponse> = reservationService.findAllReservation()

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateStatus(
        @PathVariable id: Int,
        @RequestBody requestUpdate: ReservationStatusUpdateRequest
    ): ReservationResponse = reservationService.updateStatusReservation(id, requestUpdate)
}