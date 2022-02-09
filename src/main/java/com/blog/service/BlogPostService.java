package com.blog.service;

import com.blog.entity.BlogPost;

import java.util.List;

public interface BlogPostService {

    BlogPost saveBlogPost(BlogPost blogPost);

    List<BlogPost> fetchBlogPostList();

    BlogPost fetchBlogPostById(Long id);

    void deleteBlogPostById(Long id);

    BlogPost updateBlogPost(Long id, BlogPost blogPost);

    List<BlogPost> fetchAllBlogPostByTitle(String title);

    List<BlogPost> sortBlogPostByParameterAsc(String sortParameter);

    List<BlogPost> fetchAllBlogPostByStar(Boolean star);

    BlogPost addStarToBlogPost(Long id);
}

