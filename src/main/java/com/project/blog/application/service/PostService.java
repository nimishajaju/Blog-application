package com.project.blog.application.service;

import com.project.blog.application.payloads.PostDTO;
import com.project.blog.application.payloads.PostResponse;

import java.util.List;

public interface PostService {
// create

     PostDTO createPost(PostDTO postDTO,Integer categoryId, Integer userId);

//     update
    PostDTO updatePost (PostDTO postDTO, Integer postId);

//    delete
    void deletePost(Integer postId);

//    getAllPost
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

//    getById
    PostDTO getById(Integer postId);

//    getALLPOSTByCategory
    PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize,String sortBy, String sortDir);

//    getAllPostsByUser
    PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize,String sortBy, String sortDir);

//    search Post
    List<PostDTO> search(String keyword);



}
