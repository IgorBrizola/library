package br.com.library.dto.book.reservation.response

import br.com.library.model.book.Book
import br.com.library.model.book.reservation.Reservation.ReservationStatus
import br.com.library.model.user.User
import java.time.LocalDateTime

data class ReservationResponse(
    val id: Int?,
    val user: User,
    val book: Book,
    val status: ReservationStatus,
    val createdAt: LocalDateTime,
    val active: Boolean
)
