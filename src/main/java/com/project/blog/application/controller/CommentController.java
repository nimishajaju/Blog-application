package com.project.blog.application.controller;

import com.project.blog.application.payloads.CommentDto;
import com.project.blog.application.payloads.ResponseApi;
import com.project.blog.application.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/postId/{postId}")
    ResponseEntity<CommentDto>  createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable Integer postId
    ){
       CommentDto commentDto1= commentService.createComment(commentDto,postId);
       return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/comment/{commentId}")
    ResponseEntity<ResponseApi> deleteComment(
            @PathVariable Integer commentId
    ){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ResponseApi("comment deleted", true),HttpStatus.OK);
    }
}
