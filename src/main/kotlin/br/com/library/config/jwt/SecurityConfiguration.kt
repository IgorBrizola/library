package br.com.library.config.jwt

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider
) {
    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter,
    ): DefaultSecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("library-api/auth/**", "library-api/auth/refresh/**", "/error")
                    .permitAll()

                    .requestMatchers(HttpMethod.POST, "library-api/user")
                    .permitAll()

                    .requestMatchers(HttpMethod.POST, "library-api/reservation")
                    .hasRole("USER")

                    .requestMatchers(HttpMethod.GET, "library-api/reservation")
                    .hasAnyRole("USER", "ADMIN")

                    .requestMatchers(HttpMethod.GET, "library-api/reservation/{userId}")
                    .hasAnyRole("USER", "ADMIN")

                    .requestMatchers(HttpMethod.GET, "library-api/reservation/{bookId}")
                    .hasAnyRole("USER", "ADMIN")

                    .requestMatchers(HttpMethod.GET, "library-api/reservation/{reservationId}")
                    .hasAnyRole("USER", "ADMIN")

                    .requestMatchers(HttpMethod.PUT, "library-api/reservation/{reservationId}")
                    .hasRole("ADMIN")

                    .requestMatchers(HttpMethod.DELETE, "library-api/reservation/disable/{reservationId}")
                    .hasRole("ADMIN")

                    .requestMatchers(HttpMethod.GET, "library-api/book")
                    .hasAnyRole("USER", "ADMIN")

                    .requestMatchers(HttpMethod.GET, "library-api/book/{bookId}")
                    .hasAnyRole("USER", "ADMIN")

                    .requestMatchers(HttpMethod.GET, "library-api/book/all/{bookId}")
                    .hasAnyRole("USER", "ADMIN")

                    .requestMatchers(HttpMethod.POST, "library-api/book")
                    .hasRole("ADMIN")

                    .requestMatchers(HttpMethod.PATCH, "library-api/book/{bookId}")
                    .hasRole("ADMIN")

                    .requestMatchers(HttpMethod.PUT, "library-api/book/available/{bookId}")
                    .hasRole("ADMIN")

                    .anyRequest()
                    .authenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
}
