package br.com.library.dto.user

import br.com.library.model.user.Role

data class UserResponse(
    val id: Int?,
    val name: String,
    val age: Int,
    val email: String,
    val role: Role?,
    val active: Boolean
)