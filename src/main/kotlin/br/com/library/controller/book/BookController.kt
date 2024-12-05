package br.com.library.controller.book

import br.com.library.dto.book.request.BookRequest
import br.com.library.dto.book.response.BookResponse
import br.com.library.service.book.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("library-api/book")
class BookController (
    private val bookService: BookService
){
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerBook(
        @RequestBody bookRequest: BookRequest
    ): BookResponse = bookService.registerBook(bookRequest)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAllBook(): List<BookResponse> = bookService.findAllBook()

    // TODO: findBookId, deleteBook, updateBook
}