package br.com.library.repository.refreshToken

import br.com.library.model.refreshToken.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : JpaRepository<RefreshToken, Int>{

    @Query("SELECT r.username FROM RefreshToken r WHERE r.token = ?1")
    fun findUsernameByToken(token: String): String?

    fun findByUsername(username: String): RefreshToken?
}