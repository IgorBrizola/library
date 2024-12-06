package br.com.library.dto.book.reservation.request

import br.com.library.model.book.reservation.Reservation


data class ReservationStatusUpdateRequest(
    val status: Reservation.ReservationStatus
)
