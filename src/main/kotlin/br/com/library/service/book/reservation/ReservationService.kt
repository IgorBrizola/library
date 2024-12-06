package br.com.library.service.book.reservation

import br.com.library.dto.book.reservation.request.ReservationRequest
import br.com.library.dto.book.reservation.request.ReservationStatusUpdateRequest
import br.com.library.dto.book.reservation.response.ReservationResponse
import br.com.library.dto.exception.book.BookNotFoundException
import br.com.library.dto.exception.book.reservation.ReservationNotFoundException
import br.com.library.dto.exception.user.UserNotFoundException
import br.com.library.model.book.reservation.Reservation
import br.com.library.repository.book.BookRepository
import br.com.library.repository.book.reservation.ReservationRepository
import br.com.library.repository.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository
) {

    @Transactional
    fun createReservation(reservationRequest: ReservationRequest): ReservationResponse {

            val userId = reservationRequest.userId

            val bookId = reservationRequest.bookId

            val user = userRepository.findByIdAndActiveIsTrue(userId)
                .orElseThrow { UserNotFoundException("user with id $userId not found!")}

                val book = bookRepository.findById(bookId)
                    .orElseThrow { BookNotFoundException("book with id $bookId not found!") }

                    val reservation = Reservation(
                        user = user,
                        book = book
                    )

        reservationRepository.save(reservation)

        return ReservationResponse(
            id = reservation.id,
            user = reservation.user,
            book =  reservation.book,
            status =  reservation.status,
            createdAt = reservation.createdAt,
            active = reservation.active
        )
    }

    fun findAllReservation(): List<ReservationResponse> = reservationRepository
        .findAll()
        .map { reservation ->
        ReservationResponse(
        id = reservation.id,
        user = reservation.user,
        book =  reservation.book,
        status =  reservation.status,
        createdAt = reservation.createdAt,
        active = reservation.active)
        }

    fun updateStatusReservation(
        id: Int,
        requestUpdate: ReservationStatusUpdateRequest
    ): ReservationResponse {
        val reservation = reservationRepository.findById(id)
            .orElseThrow { ReservationNotFoundException("Reservation with id $id not found!") }

        val statusUpdate = reservation
            .copy(status = requestUpdate.status)

        reservationRepository.save(statusUpdate)

        return ReservationResponse(
            id = statusUpdate.id,
            user = statusUpdate.user,
            book =  statusUpdate.book,
            status =  statusUpdate.status,
            createdAt = statusUpdate.createdAt,
            active = statusUpdate.active
        )
    }


    // TODO: findReservationId, deleteReservation, updateStatusReservation, updateReservation

}