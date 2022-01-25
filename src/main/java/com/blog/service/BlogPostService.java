package com.blog.service;

import com.blog.entity.BlogPost;

import java.util.List;

public interface BlogPostService {
   public BlogPost saveBlogPost(BlogPost blogPost);

   public List<BlogPost> fetchBlogPostList();

   public BlogPost fetchBlogPostById(Long blogPostId);

   public void deleteBlogPostById(Long blogPostId);

   public BlogPost updateBlogPost(Long blogPostId, BlogPost blogPost);
}

