package br.com.library.controller.user

import br.com.library.dto.user.UserRequest
import br.com.library.dto.user.UserRequestUpdate
import br.com.library.dto.user.UserResponse
import br.com.library.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("library-api/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listAllUser(): List<UserResponse> = userService.listAllUser()

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(
        @PathVariable id: Int
    ): UserResponse = userService.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody userRequest: UserRequest): UserResponse = userService.createUser(userRequest)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id:Int) {
        userService.deleteUserById(id)
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(
        @PathVariable id: Int,
        @RequestBody userRequestUpdate: UserRequestUpdate
    ) = userService.updatedUserById(id, userRequestUpdate)

}