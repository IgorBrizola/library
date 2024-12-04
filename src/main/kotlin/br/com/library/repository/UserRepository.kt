package br.com.library.repository

import br.com.library.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Int> {

    fun existsByEmail(email: String): Boolean

    fun findByIdAndActiveIsTrue(id: Int): Optional<User>

    fun findAllByActiveIsTrue(): List<User>

}