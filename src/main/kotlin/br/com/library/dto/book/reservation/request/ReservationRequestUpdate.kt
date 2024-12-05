package br.com.library.dto.book.reservation.request

import br.com.library.model.book.reservation.Reservation

data class ReservationRequestUpdate(
    val status: Reservation.ReservationStatus,
)
