package com.project.blog.application.repository;

import com.project.blog.application.entity.Category;
import com.project.blog.application.entity.Post;
import com.project.blog.application.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

    Page<Post> findByCategory(Category category, Pageable pageable);

    Page<Post> findByUser(User user, Pageable pageable);

    List<Post> findByTitleContaining(String title);


}
