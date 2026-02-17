package com.project.blog.application.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1️⃣ Get Authorization header
        String requestHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        // 2️⃣ Check header starts with Bearer
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            token = requestHeader.substring(7);

            try {
                username = jwtTokenHelper.getUsernameFromToken(token);
            } catch (Exception e) {
                System.out.println("Invalid JWT Token");
            }
        } else {
            System.out.println("JWT Token does not begin with Bearer");
        }

        // 3️⃣ Authenticate user if username found
        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    this.userDetailsService.loadUserByUsername(username);

            // 4️⃣ Validate token
            if (jwtTokenHelper.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // 5️⃣ Set authentication in Spring Security
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        // 6️⃣ Continue request
        filterChain.doFilter(request, response);

    }
}