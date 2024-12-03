package br.com.library.dto.user

import java.time.LocalDate

data class UserRequest(
    val name: String,
    val dateBirth: LocalDate,
    val email: String,
    val password: String
)
