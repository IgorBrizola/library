package br.com.library.model.book

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

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
    @Column(name = "is_available")
    val isAvailable: Boolean = true
)