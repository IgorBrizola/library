package br.com.library.model.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "name")
    val name: String,
    @Column(name = "date_birth")
    val dateBirth: LocalDate,
    @Column(name = "email")
    val email: String,
    @Column(name = "password")
    val password: String,
    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    val roles: Role = Role.USER,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null,
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,
    @Column(name = "active")
    val active: Boolean = true
)

enum class Role {
    USER, ADMIN
}