package com.project.blog.application.repository;

import com.project.blog.application.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
