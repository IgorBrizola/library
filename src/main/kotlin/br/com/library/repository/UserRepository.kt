package br.com.library.repository

import br.com.library.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {

    fun existsByEmail(email: String): Boolean
}