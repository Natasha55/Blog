package com.blog.controller;

import com.blog.entity.BlogPost;
import com.blog.service.BlogPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BlogPostController.class)
class BlogPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogPostService blogPostService;

    private BlogPost blogPost;

    @BeforeEach
    void setUp(){
        blogPost = BlogPost.builder()
                .title("ooo")
                .content("who")
                .star(true)
                .id(1L)
                .build();
    }


    @Test
    void saveBlogPost() throws Exception {
        BlogPost inputBlogPost = BlogPost.builder()
                .title("ooo")
                .content("who")
                .star(true)
                .build();
        Mockito.when(blogPostService.saveBlogPost(inputBlogPost))
                .thenReturn(blogPost);

        mockMvc.perform(post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"title\":\"ooo\",\n" +
                        "\t\"content\":\"who\",\n" +
                        "\t\"star\":\"true\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchBlogPostList() {
//        Mockito.when(blogPostService.
//                .thenReturn((BlogPost) List.of(new BlogPost(1L,"ooo","who",true)));
//        this.mockMvc
//                .perform(MockMvcRequestBuilders)
//                .
    }

    @Test
    void fetchBlogPostById() throws Exception {
        Mockito.when(blogPostService.fetchBlogPostById(1L))
                .thenReturn(blogPost);

        mockMvc.perform(get ("/api/v1/posts/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title")
                        .value(blogPost.getTitle()));
    }
    @Test
    void shouldCreatedMockMvc(){
        assertNotNull(mockMvc);
    }





//    @Test
//    void deleteBlogPostById() {
//        mockMvc = MockMvcRequestBuilders.
//        Mockito.when(blogPostService.deleteBlogPostById(1L)).thenReturn("Post delete successfully");
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/posts/1")
//                        .andExpect(status().isOk().isAccepted());
//                .contentType(MediaType.APPLICATION_JSON))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }



//    void updateBlogPostByTitle() {
//        Mockito.when(blogPostService.fetchBlogPostById(1L).setTitle("new").thenReturn(blogPost);
//
//        mockMvc.perform(put ("/api/v1/posts/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title")
//                        .value(blogPost.getTitle()));
//    }

    @Test
    void fetchAllBlogPostByStar() {
    }

    @Test
    void addBlogPostStar() {
    }
}