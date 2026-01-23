package com.project.blog.application.repository;

import com.project.blog.application.entity.Category;
import com.project.blog.application.entity.Post;
import com.project.blog.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

    List<Post> findByTitleContaining(String title);


}
