package br.com.library.repository.book.reservation

import br.com.library.model.book.reservation.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository: JpaRepository<Reservation, Int> {
}