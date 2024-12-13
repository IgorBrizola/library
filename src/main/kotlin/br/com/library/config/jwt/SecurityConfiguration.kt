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
            .authorizeHttpRequests{
                it
                    .requestMatchers("library-api/auth", "library-api/auth/refresh", "/error")
                    .permitAll()

                    .requestMatchers(HttpMethod.POST, "/library-api/user")
                    .permitAll()

                    .requestMatchers(HttpMethod.PATCH, "/library-api/user/{id}")
                    .hasRole("USER")

                    .requestMatchers(HttpMethod.GET, "/library-api/book/**")
                    .hasRole("USER")

                    .requestMatchers(HttpMethod.POST, "/library-api/reservation")
                    .hasRole("USER")

                    .requestMatchers(HttpMethod.GET, "/library-api/reservation/**")
                    .hasRole("USER")

                    .requestMatchers("library-api/user/**", "library-api/book/**", "library-api/reservation/**" )
                    .hasRole("ADMIN")
                    .anyRequest()
                    .fullyAuthenticated()
            }
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
}