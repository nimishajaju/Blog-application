package com.project.blog.application.service.impl;

import com.project.blog.application.entity.Comment;
import com.project.blog.application.entity.Post;
import com.project.blog.application.exceptions.ResourceNotFoundException;
import com.project.blog.application.payloads.CommentDto;
import com.project.blog.application.repository.CommentRepo;
import com.project.blog.application.repository.PostRepo;
import com.project.blog.application.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post= postRepo.findById(postId).orElse(null);
        if(post==null){
            throw new ResourceNotFoundException("post", "postId", postId);
        }

Comment comment= this.dtoToComment(commentDto);
        comment.setPost(post);
       Comment comment1= commentRepo.save(comment);
      CommentDto commentDto1= this.commentToDto(comment1);

        return commentDto1;
    }

    @Override
    public void deleteComment(Integer commentId) {
       Comment comment= commentRepo.findById(commentId).orElse(null);
       if (comment==null){
           throw new ResourceNotFoundException("comment", "commentId", commentId);
       }

       commentRepo.delete(comment);

    }

    public CommentDto commentToDto(Comment comment){
      CommentDto commentDto= modelMapper.map(comment,CommentDto.class);
        return commentDto;
    }

    public Comment dtoToComment(CommentDto commentDto){
        Comment comment= modelMapper.map(commentDto, Comment.class);


        return comment;
    }
}
