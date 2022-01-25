package com.blog.service;

import com.blog.controller.Exception;
import com.blog.entity.BlogPost;
import com.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class BlogPostServiceImplementation implements BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Override
    public BlogPost saveBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @Override
    public List<BlogPost> fetchBlogPostList() {
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost fetchBlogPostById(Long blogPostId) {
        if (blogPostRepository.findById(blogPostId).isPresent()) {
            return blogPostRepository.findById(blogPostId).get();
        }
        throw new Exception(blogPostId);
    }

    @Override
    public void deleteBlogPostById(Long blogPostId) {
        blogPostRepository.deleteById(blogPostId);
    }

    @Override
    public BlogPost updateBlogPost(Long blogPostId, BlogPost blogPost) {
        BlogPost blogPostDataBase = blogPostRepository.findById(blogPostId).get();


        if (Objects.nonNull(blogPost.getBlogPostTitle()) &&
                !"".equalsIgnoreCase(blogPost.getBlogPostTitle())) {
            blogPostDataBase.setBlogPostTitle(blogPost.getBlogPostTitle());
        }

        if (Objects.nonNull(blogPost.getBlogPostContent()) &&
                !"".equalsIgnoreCase(blogPost.getBlogPostContent())) {
            blogPostDataBase.setBlogPostContent(blogPost.getBlogPostContent());
        }
        return blogPostRepository.save(blogPostDataBase);
    }

}
