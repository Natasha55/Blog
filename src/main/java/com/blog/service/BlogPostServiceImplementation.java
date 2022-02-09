package com.blog.service;

import com.blog.controller.BlogPostNotFoundException;
import com.blog.entity.BlogPost;
import com.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Boolean.*;

@Service
public class BlogPostServiceImplementation implements BlogPostService {

    @Autowired
    private final BlogPostRepository blogPostRepository;

    public BlogPostServiceImplementation(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public BlogPost saveBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @Override
    public List<BlogPost> fetchBlogPostList() {
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost fetchBlogPostById(Long id) {
        if (blogPostRepository.findById(id).isPresent()) {
            return blogPostRepository.findById(id).get();
        }
        throw new BlogPostNotFoundException(id);
    }

    @Override
    public void deleteBlogPostById(Long id) {
        blogPostRepository.deleteById(id);
    }

    @Override
    public BlogPost updateBlogPost(Long id, BlogPost blogPost) {
        BlogPost blogPostDataBase = blogPostRepository.findById(id).get();


        if (Objects.nonNull(blogPost.getTitle()) &&
                !"".equalsIgnoreCase(blogPost.getTitle())) {
            blogPostDataBase.setTitle(blogPost.getTitle());
        }

        if (Objects.nonNull(blogPost.getContent()) &&
                !"".equalsIgnoreCase(blogPost.getContent())) {
            blogPostDataBase.setContent(blogPost.getContent());
        }
        return blogPostRepository.save(blogPostDataBase);
    }

    @Override
    public List<BlogPost> fetchAllBlogPostByTitle(String title) {
        return blogPostRepository.findByTitleIgnoreCase(title);
    }

    @Override
    public List<BlogPost> sortBlogPostByParameterAsc(String sortParameter) {
        return blogPostRepository.findAll(Sort.by(Sort.Direction.ASC, sortParameter));
    }

    @Override
    public List<BlogPost> fetchAllBlogPostByStar(Boolean star) {
        if (star == FALSE) {
            return blogPostRepository.findAll();
        } else {
            return blogPostRepository.findByStar(TRUE);
        }
    }

    @Override
    public BlogPost addStarToBlogPost(Long id) {
        Optional<BlogPost> blogPostDataBase = blogPostRepository.findById(id);

        if (blogPostDataBase.isPresent()) {
           BlogPost blogPost = blogPostDataBase.get();
           blogPost.setStar(TRUE);
           return blogPostRepository.save(blogPost);
        }

        throw new BlogPostNotFoundException(id);
    }

    @Override
    public BlogPost deleteBlogPostStar(Long id) {
        Optional<BlogPost> blogPostDataBase = blogPostRepository.findById(id);
        if (blogPostDataBase.isPresent()){
            BlogPost blogPost = blogPostDataBase.get();
            blogPost.setStar(FALSE);
            return blogPostRepository.save(blogPost);
        }
        throw new BlogPostNotFoundException(id);
    }

}


