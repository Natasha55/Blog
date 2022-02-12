package com.blog.repository;

import com.blog.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    public List<BlogPost> findByTitleIgnoreCase(String title);

    public List<BlogPost> findByStar(Boolean star);
}
