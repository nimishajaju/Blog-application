package com.project.blog.application.controller;

import com.project.blog.application.Security.CustomUserDetailService;
import com.project.blog.application.Security.JwtTokenHelper;
import com.project.blog.application.payloads.JwtAuthRequest;
import com.project.blog.application.payloads.JwtAuthResponse;
import com.project.blog.application.payloads.UserDTO;
import com.project.blog.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

        // ✅ Spring will inject these automatically
        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private CustomUserDetailService userDetailService;

        @Autowired
        private JwtTokenHelper jwtTokenHelper;

        @Autowired
        private UserService userService;


        // ✅ LOGIN API
        @PostMapping("/login")
        public ResponseEntity<JwtAuthResponse> login(
                @RequestBody JwtAuthRequest request) {

            // 1️⃣ authenticate email & password
            authenticate(request.getEmail(), request.getPassword());

            // 2️⃣ load user details from DB
            UserDetails userDetails =
                    userDetailService.loadUserByUsername(request.getEmail());

            // 3️⃣ generate JWT token
            String token = jwtTokenHelper.generateToken(userDetails);

            // 4️⃣ send token in response
            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(token);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }


        // ✅ Authentication method
        private void authenticate(String email, String password) {

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email, password);

            authenticationManager.authenticate(authenticationToken);
        }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> resgisterUser(
            @RequestBody UserDTO userDTO
    ){
        UserDTO userDTO1= userService.registerNewUser(userDTO);
        return new ResponseEntity<>(userDTO1,HttpStatus.CREATED);
    }
    }

