package br.com.library.service.book

import br.com.library.dto.book.request.BookRequest
import br.com.library.dto.book.response.BookResponse
import br.com.library.model.book.Book
import br.com.library.repository.book.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookRepository: BookRepository
) {


    @Transactional
    fun registerBook(bookRequest: BookRequest): BookResponse {
        val book = Book(
            title = bookRequest.title,
            author = bookRequest.author,
            genre = bookRequest.genre
        )

        bookRepository.save(book)

        val newBook = BookResponse(
            id = book.id,
            title = book.title,
            author = book.author,
            genre = book.genre,
            isAvailable = book.isAvailable
        )
        return newBook
    }

    @Transactional(readOnly = true)
    fun findAllBook(): List<BookResponse> = bookRepository.findAll().map {
        book ->
        BookResponse(id = book.id,
            title = book.title,
            author = book.author,
            genre = book.genre,
            isAvailable = book.isAvailable)
    }

    // TODO: findBookId, deleteBook, updateBook
}