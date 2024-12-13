package br.com.library.model.refreshToken

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "refresh_token")
data class RefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "token", unique = true)
    val token: String,

    @Column(name = "username")
    var username: String?,

    @Column(name = "date_created")
    val dateCreated: LocalDateTime = LocalDateTime.now()
)
