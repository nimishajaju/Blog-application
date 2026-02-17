package com.project.blog.application.config;

import com.project.blog.application.Security.CustomUserDetailService;
import com.project.blog.application.Security.JwtAuthenticationEntryPoint;
import com.project.blog.application.Security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableWebMvc
public class SecurityConfig{

        private final CustomUserDetailService customUserDetailService;
        private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        public SecurityConfig(
                CustomUserDetailService customUserDetailService,
                JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                JwtAuthenticationFilter jwtAuthenticationFilter) {

            this.customUserDetailService = customUserDetailService;
            this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
            this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }

        public static final String[] PUBLIC_URLS={
                "/auth/**",
                "/v3/api-docs/**",
                "/v2/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**"
        };

        // ✅ Password encoder
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        // ✅ Authentication Manager (used in login API)
        @Bean
        public AuthenticationManager authenticationManager(
                AuthenticationConfiguration config) throws Exception {

            return config.getAuthenticationManager();
        }

        // ✅ Main Security Configuration
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http)
                throws Exception {

            http
                    // ❌ Disable CSRF (JWT is stateless)
                    .csrf(csrf -> csrf.disable())

                    // ✅ Handle unauthorized access
                    .exceptionHandling(ex ->
                            ex.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    )

                    // ✅ No session (JWT based auth)
                    .sessionManagement(session ->
                            session.sessionCreationPolicy(
                                    SessionCreationPolicy.STATELESS
                            )
                    )

                    // ✅ API authorization rules
                    .authorizeHttpRequests(auth -> auth

                            // login API public
                            .requestMatchers(PUBLIC_URLS).permitAll()
                            .requestMatchers(HttpMethod.GET).permitAll()

                            // all other APIs protected
                            .anyRequest().authenticated()
                    );

            // ✅ Tell Spring to use our UserDetailsService
            http.userDetailsService(customUserDetailService);

            // ✅ Add JWT filter BEFORE Spring login filter
            http.addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

            return http.build();
        }
    }

//    private final CustomUserDetailService customUserDetailService;
//
//    public SecurityConfig(CustomUserDetailService customUserDetailService){
//        this.customUserDetailService=customUserDetailService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .authorizeHttpRequests(auth ->auth.anyRequest().authenticated()
//                )
//                .userDetailsService(customUserDetailService)
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }



