package com.project.blog.application.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "title",nullable = false, length = 100)
    private String title;

@Column(length =1000,nullable = false)
    private String content;

@Column(nullable = false)
    private  String imageName;

@Column(nullable = false)
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private  User user;
}
