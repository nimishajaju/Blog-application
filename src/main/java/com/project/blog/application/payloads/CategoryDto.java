package com.project.blog.application.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.crypto.Mac;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer category_id;

    @NotEmpty
    @Size(min = 4, message = "title should be atLeast 4 character")
    private  String category_title;

    @NotEmpty
    @Size(min = 10,message = "description should be atleast 10 characters")
    private String category_description;

}
