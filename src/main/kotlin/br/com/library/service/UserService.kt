package br.com.library.service

import br.com.library.dto.exception.UserAlreadyExistsException
import br.com.library.dto.user.UserRequest
import br.com.library.dto.user.UserResponse
import br.com.library.model.User
import br.com.library.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Period

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun createUser(userRequest: UserRequest): UserResponse {

        val userExist = userRepository.existsByEmail(userRequest.email)

        if (userExist) throw UserAlreadyExistsException("User with email '${userRequest.email}' already exists.")

        val user = User(name = userRequest.name, dateBirth = userRequest.dateBirth, email = userRequest.email, password = userRequest.password)

        val newUser = userRepository.save(user)

        return UserResponse(
            id = newUser.id,
            name = newUser.name,
            age = calcAge(dateBirth = newUser.dateBirth),
            email = newUser.email,
            role = newUser.roles
          )
    }

    fun listAllUser(): List<UserResponse> =
        userRepository.findAll().map { user -> UserResponse(id = user.id, name = user.name, age = calcAge(dateBirth = user.dateBirth), email = user.email, role = user.roles) }

    private fun calcAge(dateBirth: LocalDate, today: LocalDate = LocalDate.now()): Int {
        val age = Period.between(dateBirth, today).years
        return age
    }
}
