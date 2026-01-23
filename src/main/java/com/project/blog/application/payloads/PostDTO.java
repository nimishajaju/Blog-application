package com.project.blog.application.payloads;

import com.project.blog.application.entity.Category;
import com.project.blog.application.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private Integer postId;

    private String post_title;

    private String content;

    private  String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDTO user;
}
