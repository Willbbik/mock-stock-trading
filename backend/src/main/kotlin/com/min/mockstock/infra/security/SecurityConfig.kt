package com.min.mockstock.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val loginSuccessHandler: LoginSuccessHandler,
    private val loginFailureHandler: LoginFailureHandler
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/trade/**").authenticated() // 거래 API는 인증 필요
//                    .requestMatchers("/", "/login", "/signup").permitAll() // 인증 없이 허용
                    .anyRequest().permitAll()
            }
            .formLogin {
                it.loginProcessingUrl("/api/user/signin")
                it.usernameParameter("loginId")
                it.defaultSuccessUrl("/", true)
                it.successHandler(loginSuccessHandler)
                it.failureHandler(loginFailureHandler)
                it.permitAll()
            }
            .logout {
                it.logoutUrl("/logout")
                it.logoutSuccessUrl("/")
            }

        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}