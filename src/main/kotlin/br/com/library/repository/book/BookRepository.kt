package br.com.library.repository.book

import br.com.library.model.book.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Int> {
}