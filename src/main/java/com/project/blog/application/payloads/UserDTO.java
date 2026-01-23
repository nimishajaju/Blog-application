package com.project.blog.application.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "Name should be atleast 4 characters")
    private String name;

    @Email(message = "email is invalid")
    private String email;

    @NotEmpty
    @Size(min= 8, max = 9, message = "password should have atleast 8 characters")
    private String password;

    @NotEmpty(message = "write something in about")
    @Size(min = 10)
    private String about;
}