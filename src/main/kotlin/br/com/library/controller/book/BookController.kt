package br.com.library.controller.book

import br.com.library.dto.book.request.BookRequest
import br.com.library.dto.book.request.BookRequestAvailable
import br.com.library.dto.book.request.BookRequestUpdate
import br.com.library.dto.book.reservation.request.ReservationRequest
import br.com.library.dto.book.reservation.response.ReservationResponse
import br.com.library.dto.book.response.BookResponse
import br.com.library.service.book.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("library-api/book")
class BookController (
    private val bookService: BookService
){
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(
        @RequestBody bookRequest: BookRequest
    ): BookResponse = bookService.registerBook(bookRequest)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listAll(
      @RequestParam("title") title: String?,
      @RequestParam("author") author: String?,
      @RequestParam("genre") genre: String?
    ): List<BookResponse> = bookService.findAllBook(title, author, genre)

    @GetMapping("{bookId}")
    fun findById(
        @PathVariable bookId: Int,
    ): BookResponse = bookService.findBookByIdActiveTrue(bookId)

    @GetMapping("all/{bookId}")
    fun findByIdAllBook(
        @PathVariable bookId: Int,
    ): BookResponse = bookService.findByIdAll(bookId)

    @PutMapping("available/{bookId}")
    fun updateAvailable(
       @PathVariable bookId: Int,
       @RequestBody requestAvailable: BookRequestAvailable
    ): BookResponse = bookService.updateAvailableBook(bookId, requestAvailable)

    @PatchMapping("{bookId}")
    fun updateBook(
        @PathVariable bookId: Int,
        @RequestBody requestBookUpdate: BookRequestUpdate): BookResponse = bookService.updateBook(bookId, requestBookUpdate)
}