package br.com.library.dto.exception

class BusinessException(
    message: String?,
    val title: String? = null,
) : RuntimeException(message)