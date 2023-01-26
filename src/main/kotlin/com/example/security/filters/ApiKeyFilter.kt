package com.example.security.filters

import com.example.security.authentications.ApiKeyAuthentication
import com.example.security.manager.CustomAuthenticationManager
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class ApiKeyFilter(key: String): OncePerRequestFilter() {

    private val key: String

    init {
        this.key = key
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val manager = CustomAuthenticationManager(key)
        val requestKey = request.getHeader("X-API-KEY")

        if (requestKey == null) {
            filterChain.doFilter(request, response)
            return
        }

        val auth = ApiKeyAuthentication(requestKey)
        try {
            val authentication = manager.authenticate(auth)
            if (authentication.isAuthenticated) {
                SecurityContextHolder.getContext().authentication = authentication
                filterChain.doFilter(request, response)
            }
            else
                response.status = HttpServletResponse.SC_UNAUTHORIZED
        } catch (e: AuthenticationException) {
            e.printStackTrace()
            response.status = HttpServletResponse.SC_UNAUTHORIZED
        }
        filterChain.doFilter(request, response)
    }

}