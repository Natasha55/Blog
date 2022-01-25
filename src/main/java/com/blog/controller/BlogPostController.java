package com.blog.controller;

import com.blog.entity.BlogPost;
import com.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    public BlogPostController() {
    }

    @PostMapping("/api/v1/posts")
    public BlogPost saveBlogPost(@RequestBody BlogPost blogPost) {
        return blogPostService.saveBlogPost(blogPost);
    }

    @GetMapping("/api/v1/posts")
    public List<BlogPost> fetchBlogPostList() {
        return blogPostService.fetchBlogPostList();
    }

    @RequestMapping(value = "/api/v1/posts/{id}", method = RequestMethod.GET)
    public BlogPost fetchBlogPostById(@PathVariable("id") Long blogPostId) {
        return blogPostService.fetchBlogPostById(blogPostId);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public String deleteBlogPostById(@PathVariable("id") Long blogPostId) {
        blogPostService.deleteBlogPostById(blogPostId);
        return "Post delete successfully";
    }

    @PutMapping("/api/v1/posts/{id}")
    public BlogPost updateBlogPost(@PathVariable("id") Long blogPostId,
                                   @RequestBody BlogPost blogPost) {
        return blogPostService.updateBlogPost(blogPostId, blogPost);
    }
}
