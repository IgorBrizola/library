package br.com.library.repository.user

import br.com.library.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Int> {

    fun existsByEmailAndActiveIsTrue(email: String): Boolean

    fun findByIdAndActiveIsTrue(id: Int): Optional<User>

    fun findAllByActiveIsTrue(): List<User>

}