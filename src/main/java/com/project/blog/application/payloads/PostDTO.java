package com.project.blog.application.payloads;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.blog.application.entity.Category;
import com.project.blog.application.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({
        "postId",
        "title",
        "content",
        "imageName",
        "addedDate",
        "category",
        "user",
        "comments"
})
public class PostDTO {

    private Integer postId;

    private String title;

    private String content;

    private  String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDTO user;

    private Set<CommentDto> comments=new HashSet<>();
}
