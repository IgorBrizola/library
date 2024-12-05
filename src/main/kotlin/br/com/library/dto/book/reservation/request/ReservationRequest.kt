package br.com.library.dto.book.reservation.request


data class ReservationRequest(
    val userId: Int,
    val bookId: Int
)
