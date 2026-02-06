package com.project.blog.application.controller;

import com.project.blog.application.config.AppConstant;
import com.project.blog.application.payloads.PostDTO;
import com.project.blog.application.payloads.PostResponse;
import com.project.blog.application.payloads.ResponseApi;
import com.project.blog.application.service.FileService;
import com.project.blog.application.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
            @RequestParam  (value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir){
        PostResponse posts= postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/postId/{postId}/posts")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
        PostDTO postById = postService.getById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }

    @GetMapping("/categoryId/{categoryId}/posts")
    public ResponseEntity<PostResponse> getpostsByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){

      PostResponse  postsByCategory=  postService.getAllPostByCategory(categoryId,pageNumber,pageSize, sortBy,sortDir);
      return new ResponseEntity<>(postsByCategory,HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}/posts")
    public ResponseEntity<PostResponse> getPostByUser(
            @PathVariable Integer userId,
            @RequestParam (value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam (value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir ){
        PostResponse postByUser= postService.getAllPostByUser(userId, pageNumber, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postByUser,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(
            @PathVariable String keyword
    ){
        List<PostDTO>  postDTOS = postService.search(keyword);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }


    @PostMapping("/post/postId/{postId}")
    public ResponseEntity<PostDTO> uploadImage(
            @PathVariable Integer postId,
            @RequestParam MultipartFile image) throws IOException {
       PostDTO postDTO= postService.getById(postId);
       String imageName= fileService.uploadImage(path,image);

       postDTO.setImageName(imageName);

      PostDTO updateImageName= postService.updatePost(postDTO,postId);
      return  new ResponseEntity<>(updateImageName,HttpStatus.OK);


    }

}
