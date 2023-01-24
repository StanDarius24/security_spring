package com.example.security.config.security.filters

import com.example.security.config.security.authentication.CustomAuthentication
import com.example.security.config.security.manager.CustomAuthenticationManager
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.AllArgsConstructor
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@AllArgsConstructor
class CustomAuthenticationFilter(customAuthenticationManager: CustomAuthenticationManager) : OncePerRequestFilter() {
// in the filter chain there is no guarantee that the filter is only one time called (extend FILTER INTERFACE)

    private final val customAuthenticationManager: CustomAuthenticationManager

    init {
        this.customAuthenticationManager = customAuthenticationManager
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        // 1. create an authentication object which is not yet authenticated
        // 2. delegate the authentication object to the manager
        // 3. get back the authentication from the manager
        // 4. if the object is authenticated then send request to the next filter in the chain

        val key = request.getHeader("key")
        val customAuthentication = CustomAuthentication(false, key)

        val a = customAuthenticationManager.authenticate(customAuthentication)

        if (a != null) {
            if (a.isAuthenticated) {
                SecurityContextHolder.getContext().authentication = a
                filterChain.doFilter(request, response) // propagate to the next filter :D
            }
        }
    }

}