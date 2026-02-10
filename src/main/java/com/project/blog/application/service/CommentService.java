package com.project.blog.application.service;

import com.project.blog.application.payloads.CommentDto;
import org.springframework.stereotype.Service;


public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}
