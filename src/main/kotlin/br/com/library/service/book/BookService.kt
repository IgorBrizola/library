package br.com.library.service.book

import br.com.library.dto.book.request.BookRequest
import br.com.library.dto.book.request.BookRequestAvailable
import br.com.library.dto.book.response.BookResponse
import br.com.library.dto.exception.book.BookNotFoundException
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
            available = book.available
        )
        return newBook
    }

    @Transactional(readOnly = true)
    fun findAllBook(): List<BookResponse> = bookRepository.findAllByAvailableIsTrue().map {
        book ->
        BookResponse(id = book.id,
            title = book.title,
            author = book.author,
            genre = book.genre,
            available = book.available
        )
    }


    @Transactional(readOnly = true)
    fun findBookById(bookId: Int): BookResponse = bookRepository.findByIdAndAvailableIsTrue(bookId)
        .map {
                book ->
            BookResponse(id = book.id,
                title = book.title,
                author = book.author,
                genre = book.genre,
                available = book.available
            )
        }.orElseThrow { BookNotFoundException("book with id $bookId not found!") }




    @Transactional
    fun updateAvailableBook(bookId: Int, requestAvailableUpdate: BookRequestAvailable): BookResponse {
        val book = bookRepository.findById(bookId)
            .orElseThrow { BookNotFoundException("book with id $bookId not found!") }

        val updateAvailable = book.copy(
            available = requestAvailableUpdate.available
        )

        bookRepository.save(updateAvailable)

        return BookResponse(
            id = book.id,
            title = book.title,
            author = book.author,
            genre = book.genre,
            available = book.available
        )
    }

    // TODO: findBookId, deleteBook, updateBook
}