package br.com.library.service.user

import br.com.library.dto.exception.NullPointerException
import br.com.library.dto.exception.user.UserAlreadyExistsException
import br.com.library.dto.exception.user.UserNotFoundException
import br.com.library.dto.user.UserRequest
import br.com.library.dto.user.UserRequestUpdate
import br.com.library.dto.user.UserResponse
import br.com.library.model.user.User
import br.com.library.repository.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) {

    @Transactional
    fun createUser(userRequest: UserRequest): UserResponse {

        val userExist = userRepository.existsByEmailAndActiveIsTrue(userRequest.email)

        if (userExist) throw UserAlreadyExistsException("User with email '${userRequest.email}' already exists.")

        val user = User(name = userRequest.name, dateBirth = userRequest.dateBirth, email = userRequest.email, password = encoder.encode(userRequest.password))

        val newUser = userRepository.save(user)

        return UserResponse(
            id = newUser.id,
            name = newUser.name,
            age = calcAge(dateBirth = newUser.dateBirth),
            email = newUser.email,
            role = newUser.roles,
            active = user.active
          )
    }

    @Transactional(readOnly = true)
    fun listAllUser(): List<UserResponse> =
        userRepository.findAllByActiveIsTrue().map {
            user ->
            UserResponse(
                id = user.id,
                name = user.name,
                age = calcAge(dateBirth = user.dateBirth),
                email = user.email,
                role = user.roles,
                active = user.active
            )
        }

    @Transactional(readOnly = true)
    fun findById(id: Int): UserResponse = userRepository.findById(id)
        .map { user ->
        UserResponse(
            id = user.id ,
            name = user.name,
            age = calcAge(dateBirth = user.dateBirth),
            email = user.email ,
            role = user.roles,
            active = user.active
        ) }
        .orElseThrow { NullPointerException("user not found!") }

    @Transactional
    fun deleteUserById(id: Int)  {
        val user = userRepository.findByIdAndActiveIsTrue(id).orElseThrow{ UserNotFoundException("user with id $id not found!") }
        val updatedEmail = "${user.email.split("@")[0]}_deactivated_${LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)}@${user.email.split("@")[1]}"
        val userDeleted = user.copy(
            email = updatedEmail,
            deletedAt = LocalDateTime.now(),
            active = false)
        userRepository.save(userDeleted)
    }

    @Transactional
    fun updatedUserById(id: Int, userRequestUpdate: UserRequestUpdate) {
        val user = userRepository.findByIdAndActiveIsTrue(id).orElseThrow{ UserNotFoundException("user with id $id not found!") }
        val userUpdated = user.copy(
            name = userRequestUpdate.name ?: user.name,
            updatedAt = LocalDateTime.now()
        )
        userRepository.save(userUpdated)
    }


    private fun calcAge(dateBirth: LocalDate, today: LocalDate = LocalDate.now()): Int {
        val age = Period.between(dateBirth, today).years
        return age
    }
}
