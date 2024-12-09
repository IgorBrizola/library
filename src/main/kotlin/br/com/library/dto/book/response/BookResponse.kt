package br.com.library.dto.book.response

data class BookResponse(
    val id: Int?,
    val title: String,
    val author: String,
    val genre: String,
    val available: Boolean = true
)
