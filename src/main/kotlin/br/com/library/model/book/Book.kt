package br.com.library.model.book

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "title")
    val title: String,
    @Column(name = "author")
    val author: String,
    @Column(name = "genre")
    val genre: String,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null,
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,
    @Column(name = "is_available")
    val isAvailable: Boolean = true
)