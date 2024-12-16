package br.com.library.service.user

import br.com.library.repository.user.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val user =  userRepository.findByEmailAndActiveIsTrue(username)
            ?: throw UsernameNotFoundException("user with email $username not found!")

        println(user)

        return User.builder()
                .username(user.email)
                .password(user.password)
                .roles(user.role.name)
                .build()
    }
}