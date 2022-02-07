package com.blog.service;

import com.blog.controller.BlogPostNotFoundException;
import com.blog.entity.BlogPost;
import com.blog.repository.BlogPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.*;

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

        List<BlogPost> found =
                blogPostService.fetchAllBlogPostByTitle("test title");

        assertEquals(2, found.size());
    }

    @Test
    void fetchBlogPostById() {
        BlogPost blogPost = BlogPost.builder()
                .content("test content")
                .title("test title")
                .id(1L)
                .build();

        when(blogPostRepository.findById(1L)).thenReturn(Optional.of(blogPost));
        BlogPost actual =
                blogPostService.fetchBlogPostById(1L);
        assertEquals(1L, actual.getId());
    }

    @Test
    void fetchBlogPostByIdThrowsNotFound() {
        assertThrows(BlogPostNotFoundException.class, () -> blogPostService.fetchBlogPostById(1L));
    }

    @Test
    void deleteBlogPostById() {
        blogPostService.deleteBlogPostById(1L);
        verify(blogPostRepository).deleteById(any());
    }

    @Test
    void updateBlogPostWithTitleAndContent() {
        BlogPost dbBlog = BlogPost.builder()
                .content("test content")
                .title("test title")
                .id(1L)
                .build();
        BlogPost blogUpdated = BlogPost.builder()
                .content("content new")
                .title("title new")
                .id(1L)
                .build();
        when(blogPostRepository.findById(1L)).thenReturn(Optional.ofNullable(dbBlog));
        when(blogPostRepository.save(blogUpdated)).thenReturn(blogUpdated);
        BlogPost expectedBlogPost = blogPostService.updateBlogPost(1L, blogUpdated);
        verify(blogPostRepository, times(1)).save(blogUpdated);
        assertEquals(expectedBlogPost.getContent(), dbBlog.getContent());
    }

    @Test
    void updateBlogIfUpdateIsNull() {
        BlogPost dbBlog = BlogPost.builder()
                .content("test content")
                .title("test title")
                .id(1L)
                .build();

        BlogPost blogUpd = BlogPost.builder()
                .id(1L)
                .build();

        when(blogPostRepository.findById(1L)).thenReturn(Optional.ofNullable(dbBlog));
        when(blogPostRepository.save(dbBlog)).thenReturn(dbBlog);
        BlogPost expectedBlogPost = blogPostService.updateBlogPost(1L, blogUpd);
        verify(blogPostRepository, times(1)).save(dbBlog);
        assertEquals(expectedBlogPost.getContent(), "test content");
        assertEquals(expectedBlogPost.getTitle(), "test title");
    }

    @Test
    void updateBlogPostNoSuchElementException() {
        assertThrows(NoSuchElementException.class,
                () -> blogPostService
                        .updateBlogPost(1L, BlogPost
                                .builder().title("ne").content("za").build()));
    }

    @Test
    void sortBlogPostByTitleAsc() {
        Sort sortBy = Sort.by(Sort.Direction.ASC, "title");
        List<BlogPost> sorted = blogPostService.sortBlogPostByParameterAsc("title");
        verify(blogPostRepository, times(1)).findAll(sortBy);
    }

    @Test
    void fetchAllBlogPostByStar() {
        List<BlogPost> foundStar = blogPostService.fetchAllBlogPostByStar(Boolean.TRUE);
        verify(blogPostRepository, times(1)).findByStar(true);
    }

}
