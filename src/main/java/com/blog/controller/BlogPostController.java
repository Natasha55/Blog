package com.blog.controller;

import com.blog.entity.BlogPost;
import com.blog.service.BlogPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    public BlogPostController() {
    }

    private final Logger LOGGER = LoggerFactory.getLogger(BlogPostController.class);

    @PostMapping
    public BlogPost saveBlogPost(@Valid @RequestBody BlogPost blogPost) {
        LOGGER.info("Inside saveBlogPost of BlogPostController");
        return blogPostService.saveBlogPost(blogPost);
    }

    @GetMapping
    public List<BlogPost> fetchBlogPostList(@RequestParam(required = false) String title
            , @RequestParam(required = false) String sort) {
        LOGGER.info("Inside fetchBlogPostList of BlogPostController");
        if (title == null && sort == null) {
            return blogPostService.fetchBlogPostList();
        }
        if (sort != null) {
            return blogPostService.sortBlogPostByParameterAsc(sort);
        } else {
            return blogPostService.fetchAllBlogPostByTitle(title);
        }
    }

    @GetMapping("/{id}")
    public BlogPost fetchBlogPostById(@PathVariable("id") Long id)
            throws BlogPostNotFoundException {
        return blogPostService.fetchBlogPostById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBlogPostById(@PathVariable("id") Long id) {
        blogPostService.deleteBlogPostById(id);
        return "Post delete successfully";
    }

    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable("id") Long id,
                                   @RequestBody BlogPost blogPost)
            throws NoSuchElementException {
        return blogPostService.updateBlogPost(id, blogPost);
    }

    @GetMapping("/star")
    public List<BlogPost> fetchAllBlogPostByStar(Boolean star)
            throws BlogPostNotFoundException {
        return blogPostService.fetchAllBlogPostByStar(star);
    }

    @PutMapping("/{id}/star")
    public BlogPost addBlogPostStar(@PathVariable("id") Long id)
            throws BlogPostNotFoundException {
       return blogPostService.addStarToBlogPost(id);
    }

}
