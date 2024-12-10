package br.com.library.repository.book

import br.com.library.model.book.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface BookRepository : JpaRepository<Book, Int> {

    @Query(
         """
          SELECT b 
          FROM Book b 
          WHERE (:title IS NULL OR b.title = :title)
          AND (:author IS NULL OR b.author = :author)
          AND (:genre IS NULL OR b.genre = :genre)
          AND b.available = true
          """
    )
    fun findAll(
        @Param("title") title: String?,
        @Param("author") author: String?,
        @Param("genre") genre: String?
    ): List<Book>

    fun findByIdAndAvailableIsTrue(bookId: Int?): Optional<Book>
}