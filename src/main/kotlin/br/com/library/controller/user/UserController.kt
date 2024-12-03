package br.com.library.controller.user

import br.com.library.dto.user.UserRequest
import br.com.library.dto.user.UserResponse
import br.com.library.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("library-api/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun listAllUser(): List<UserResponse> = userService.listAllUser()

    @PostMapping
    fun registerUser(@RequestBody userRequest: UserRequest): UserResponse = userService.createUser(userRequest)
}