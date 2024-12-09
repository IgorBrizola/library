package br.com.library.repository.book.reservation

import br.com.library.model.book.reservation.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface ReservationRepository: JpaRepository<Reservation, Int> {

    fun findAllByActiveIsTrue(): List<Reservation>

    fun findByIdAndActiveIsTrue(id: Int): Optional<Reservation>

    @Query("SELECT r FROM Reservation r JOIN r.user s WHERE s.id = ?1")
    fun findAllByUserAndActiveIsTrue(userId: Int): List<Reservation>
}