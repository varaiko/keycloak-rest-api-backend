package com.keycloak.keycloak_integration.repository;

import com.keycloak.keycloak_integration.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByStoryId(long storyId);
}
