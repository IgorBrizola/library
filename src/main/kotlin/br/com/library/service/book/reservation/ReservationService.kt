package br.com.library.service.book.reservation

import br.com.library.dto.book.reservation.request.ReservationRequest
import br.com.library.dto.book.reservation.request.ReservationStatusUpdateRequest
import br.com.library.dto.book.reservation.response.ReservationResponse
import br.com.library.dto.exception.BusinessException
import br.com.library.dto.exception.book.BookNotFoundException
import br.com.library.dto.exception.book.reservation.ReservationNotFoundException
import br.com.library.dto.exception.user.UserNotFoundException
import br.com.library.model.book.reservation.Reservation
import br.com.library.model.book.reservation.Reservation.ReservationStatus.IN_PROGRESS
import br.com.library.model.book.reservation.Reservation.ReservationStatus.PENDING
import br.com.library.repository.book.BookRepository
import br.com.library.repository.book.reservation.ReservationRepository
import br.com.library.repository.user.UserRepository
import br.com.library.service.jwt.TokenService
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository,
    private val tokenService: TokenService
    ) {

    @Transactional
    fun createReservation(reservationRequest: ReservationRequest, authorization: String?): ReservationResponse {

        if (authorization == null || !authorization.startsWith("Bearer ")) throw BadRequestException("Token invalid!")

        val token = authorization.substringAfter("Bearer ").trim()

        val email = tokenService.extractEmail(token) ?: throw UserNotFoundException("Email user not found!")

        val userId = userRepository.findByEmailAndActiveIsTrue(email)?.id

        if (findUserIdWithReservation(userId!!).count() > 4) throw BusinessException("User cannot have four active reservations")

        val bookId = reservationRequest.bookId

            val user = userRepository.findByIdAndActiveIsTrue(userId)
                .orElseThrow { UserNotFoundException("user with id $userId not found!")}

                val book = bookRepository.findByIdAndAvailableIsTrue(bookId)
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

    @Transactional(readOnly = true)
    fun findAllReservation(): List<ReservationResponse> = reservationRepository
        .findAllByActiveIsTrue()
        .map { reservation ->
        ReservationResponse(
        id = reservation.id,
        user = reservation.user,
        book =  reservation.book,
        status =  reservation.status,
        createdAt = reservation.createdAt,
        active = reservation.active)
        }

    @Transactional
    fun updateStatusReservation(
        id: Int,
        requestUpdate: ReservationStatusUpdateRequest
    ): ReservationResponse {

        val reservation = reservationRepository.findByIdAndActiveIsTrue(id)
            .orElseThrow { ReservationNotFoundException("Reservation with id $id not found!") }

        val book = reservation.book

        val avaiable = reservation.book.available

        if (!avaiable) throw BadRequestException("Book not available!")

        val statusUpdate = reservation.copy(
            status = requestUpdate.status,
            active = requestUpdate.status == IN_PROGRESS || requestUpdate.status == PENDING ,
            book = book.copy(available = requestUpdate.status != IN_PROGRESS)
        )

        reservationRepository.save(statusUpdate)

        bookRepository.save(statusUpdate.book)

        return ReservationResponse(
            id = statusUpdate.id,
            user = statusUpdate.user,
            book =  statusUpdate.book,
            status =  statusUpdate.status,
            createdAt = statusUpdate.createdAt,
            active = statusUpdate.active
        )
    }

    @Transactional(readOnly = true)
    fun findUserIdWithReservation(userId: Int): List<ReservationResponse> = reservationRepository.findAllByUserIdAndActiveIsTrue(userId)
        .map { reservation -> ReservationResponse(
            id = reservation.id,
            user = reservation.user,
            book =  reservation.book,
            status =  reservation.status,
            createdAt = reservation.createdAt,
            active = reservation.active
        ) }

    @Transactional(readOnly = true)
    fun findBookIdWithReservation(bookId: Int): List<ReservationResponse> = reservationRepository.findAllByBookIdAndActiveIsTrue(bookId)
        .map { reservation -> ReservationResponse(
            id = reservation.id,
            user = reservation.user,
            book =  reservation.book,
            status =  reservation.status,
            createdAt = reservation.createdAt,
            active = reservation.active
        ) }

    @Transactional
    fun disableReservation(
        id: Int
    ) {
        val reservation = reservationRepository.findByIdAndActiveIsTrue(id).orElseThrow { ReservationNotFoundException("Reservation with id $id not found!") }
        val reservationDisable = reservation.copy(
            deletedAt = LocalDateTime.now(),
            active = false
        )
        reservationRepository.save(reservationDisable)
    }

    @Transactional(readOnly = true)
    fun findById(id: Int): ReservationResponse = reservationRepository.findByIdAndActiveIsTrue(id)
        .map {
            reservation -> ReservationResponse(
            id = reservation.id,
            user = reservation.user,
            book =  reservation.book,
            status =  reservation.status,
            createdAt = reservation.createdAt,
            active = reservation.active
        )  }
        .orElseThrow { ReservationNotFoundException("Reservation with id $id not found!") }
}