package br.com.library.dto.auth

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String
)
