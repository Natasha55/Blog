package com.blog.service;

import com.blog.entity.BlogPost;
import com.blog.repository.BlogPostRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BlogPostServiceTest {

    @Autowired
    private BlogPostService blogPostService;

    @Mock
    private BlogPostRepository blogPostRepository;

    @BeforeEach
    void initUseCase() {
        blogPostService = new BlogPostServiceImplementation(blogPostRepository);
    }

    @BeforeEach
    void setUp() {

    }

    @Test()
    void saveBlogPostTriggerOnlyOneSaveToRepository() {
        BlogPost blogPost = BlogPost.builder()
                .content("test content")
                .title("test title")
                .build();

        blogPostService.saveBlogPost(blogPost);
        verify(blogPostRepository, times(1)).save(any(BlogPost.class));
    }

    @Test()
    void saveBlogPost() {
        BlogPost dbBlog = BlogPost.builder()
                .content("test content")
                .title("test title")
                .id(1L)
                .build();

        BlogPost blogPost = BlogPost.builder()
                .content("test content")
                .title("test title")
                .build();

        when(blogPostRepository.save(any(BlogPost.class))).thenReturn(dbBlog);

        BlogPost actualBlogPost = blogPostService.saveBlogPost(blogPost);
        assertEquals(1L, actualBlogPost.getId());
    }


    @Test
    void fetchBlogPostList() {

        BlogPost blogPost = BlogPost.builder()
                .content("test content")
                .title("test title")
                .build();

        List<BlogPost> blogPostList = new ArrayList<>();
        blogPostList.add(blogPost);
        blogPostList.add(blogPost);

        when(blogPostRepository.findByTitleIgnoreCase("test title")).thenReturn(blogPostList);

        List<BlogPost> found  =
                blogPostService.fetchAllBlogPostByTitle("test title");

        assertEquals(2, found.size());

    }

    @Test
    void fetchBlogPostById() {
        BlogPost blogPost = BlogPost.builder()
                .content("test content")
                .title("test title")
                .build();


    }

    @Test
    void deleteBlogPostById() {
    }

    @Test
    void updateBlogPost() {
    }

//    @Test
//    void fetchAllBlogPostByTitle() {
//        BlogPost blogPost1 = BlogPost.builder()
//                .content("title1")
//                .title("one")
////                .id(1L)
//                .build();
//        BlogPost blogPost2 = BlogPost.builder()
//                .content("title2")
//                .title("two")
////                .id(1L)
//                .build();
//        String actualBlogPostTitle = "one";
//
//        List<BlogPost> found = List.of(blogPost1, blogPost2);
//               found = blogPostService.fetchAllBlogPostByTitle("one");
//        assertEquals(found.stream().findAny(found.equals(title("one"))), found.get());
//
//    }

    @Test
    void sortBlogPostByTitleAsc() {
    }

    @Test
    void fetchAllBlogPostByStar() {
    }
}