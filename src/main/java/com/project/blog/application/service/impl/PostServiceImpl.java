package com.project.blog.application.service.impl;

import com.project.blog.application.entity.Category;
import com.project.blog.application.entity.Post;
import com.project.blog.application.entity.User;
import com.project.blog.application.exceptions.ResourceNotFoundException;
import com.project.blog.application.payloads.PostDTO;
import com.project.blog.application.payloads.PostResponse;
import com.project.blog.application.repository.CategoryRepo;
import com.project.blog.application.repository.PostRepo;
import com.project.blog.application.repository.UserRepository;
import com.project.blog.application.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepository userRepository;


    @Override
    public PostDTO createPost(PostDTO postDTO,Integer categoryId, Integer userId) {
        Category category= categoryRepo.findById(categoryId).orElse(null);
        if (category== null){
            throw new ResourceNotFoundException("category", "categoryId", categoryId);
        }

        User user = userRepository.findById(userId).orElse(null);
        if(user== null){
            throw new ResourceNotFoundException("user", "userId", userId);
        }

        Post post= this.dtoToPost(postDTO);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);

        Post savePost= postRepo.save(post);
        return this.posttoDto(savePost);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post= postRepo.findById(postId).orElse(null);
        if(post== null){
            throw  new ResourceNotFoundException("post", "postId", postId);
        }
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

        Post updatePost =  postRepo.save(post);

        return this.posttoDto(updatePost);
    }

    @Override
    public void deletePost(Integer postId) {
     Post post = postRepo.findById(postId).orElse(null);
     if (post==null){
         throw new ResourceNotFoundException("post","postId", postId);
     }
     postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort = sort.by(sortBy).ascending();
        } else {
            sort= sort.by(sortBy).descending();
        }
        Pageable p = PageRequest. of(pageNumber,pageSize,sort);
        Page<Post> allPost= postRepo.findAll(p);
        List<Post> allposts= allPost.getContent();

       List<PostDTO> postDTOS= allposts.stream().map((post) -> posttoDto(post) ).collect(Collectors.toList());
       PostResponse postResponse = new PostResponse();
       postResponse.setContent(postDTOS);
       postResponse.setPageNumber(allPost.getNumber());
       postResponse .setPageSize(allPost.getSize());
       postResponse.setTotalElements(allPost.getTotalElements());
       postResponse.setTotalPages(allPost.getTotalPages());
       postResponse.setLastPage(allPost.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getById(Integer postId) {
        Post post = postRepo.findById(postId).orElse(null);
        if(post==null){
            throw new ResourceNotFoundException("post", "postId", postId);
        }
        return this.posttoDto(post);
    }

    @Override
    public List<PostDTO> getAllPostByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElse(null);
        if (category== null){
            throw  new ResourceNotFoundException("category","categoryId", categoryId);
        }
        List<Post> allPostByCategory= postRepo.findByCategory(category);
       List<PostDTO> allPostsByCategory= allPostByCategory.stream().map((post )-> posttoDto(post)).collect(Collectors.toList());
        return allPostsByCategory;
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user==null){
            throw new ResourceNotFoundException("user", "userId", userId);
        }
        List<Post> allPostByUser= postRepo.findByUser(user);
        List<PostDTO>  allPostsByUser= allPostByUser.stream().map((post)-> posttoDto(post)).collect(Collectors.toList());
        return allPostsByUser;
    }

    @Override
    public List<PostDTO> search(String keyword) {
        List<Post> posts= postRepo.findByTitleContaining(keyword);
        List<PostDTO> postDTOS= posts.stream().map((post)-> posttoDto(post)).collect(Collectors.toList());

        return postDTOS;
    }

    public PostDTO posttoDto(Post post){
        PostDTO postDTO= modelMapper.map(post,PostDTO.class);
        return postDTO;
    }

    public Post dtoToPost(PostDTO postDTO){
        Post post= modelMapper.map(postDTO,Post.class);
        return  post;
    }
}
