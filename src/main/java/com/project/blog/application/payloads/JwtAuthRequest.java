package com.project.blog.application.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthRequest {

    private String email;
    private String password;
}
