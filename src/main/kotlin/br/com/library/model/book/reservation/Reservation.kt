package br.com.library.model.book.reservation

import br.com.library.model.book.Book
import br.com.library.model.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "reservation")
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @OneToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @OneToOne
    @JoinColumn(name = "book_id")
    val book: Book,
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: ReservationStatus = ReservationStatus.PENDING,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,
    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null,
    @Column(name = "active")
    val active: Boolean = true
) {
    enum class ReservationStatus{
        PENDING, APPROVED, REJECTED, COMPLETED
    }
}