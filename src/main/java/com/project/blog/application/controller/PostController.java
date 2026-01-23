package com.project.blog.application.controller;

import com.project.blog.application.payloads.PostDTO;
import com.project.blog.application.payloads.PostResponse;
import com.project.blog.application.payloads.ResponseApi;
import com.project.blog.application.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/categoryId/{categoryId}/userId/{userId}/posts")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO,
                                              @PathVariable Integer categoryId,
                                              @PathVariable Integer userId){
      PostDTO saved=   postService.createPost(postDTO,categoryId,userId);
      return new ResponseEntity<PostDTO>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/postId/{postId}/posts")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO,
                                              @PathVariable Integer postId){
        PostDTO updatedPost = postService.updatePost(postDTO,postId);
        return  new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/postId/{postId}/posts")
    public ResponseEntity<ResponseApi> deletePost(@PathVariable Integer postId ){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ResponseApi("post deleted",true),HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam  (value = "pageNumber",defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
        PostResponse posts= postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/postId/{postId}/posts")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
        PostDTO postById = postService.getById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }

    @GetMapping("/categoryId/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getpostsByCategory(@PathVariable Integer categoryId){
      List<PostDTO>  postsByCategory=  postService.getAllPostByCategory(categoryId);
      return new ResponseEntity<>(postsByCategory,HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
        List<PostDTO> postByUser= postService.getAllPostByUser(userId);
        return new ResponseEntity<>(postByUser,HttpStatus.OK);
    }
}
