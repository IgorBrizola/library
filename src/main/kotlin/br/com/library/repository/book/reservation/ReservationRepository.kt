package br.com.library.repository.book.reservation

import br.com.library.model.book.reservation.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ReservationRepository: JpaRepository<Reservation, Int> {

    fun findAllByActiveIsTrue(): List<Reservation>

    fun findByIdAndActiveIsTrue(id: Int): Optional<Reservation>

    fun findAllByBookIdAndActiveIsTrue(bookId: Int): List<Reservation>

    fun findAllByUserIdAndActiveIsTrue(userId: Int): List<Reservation>
}