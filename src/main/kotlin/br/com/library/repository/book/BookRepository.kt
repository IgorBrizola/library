package br.com.library.repository.book

import br.com.library.model.book.Book
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface BookRepository : JpaRepository<Book, Int> {

    fun findAllByAvailableIsTrue(): List<Book>

    fun findByIdAndAvailableIsTrue(id: Int?): Optional<Book>
}