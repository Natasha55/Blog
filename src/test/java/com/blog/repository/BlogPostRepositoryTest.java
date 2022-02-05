package com.blog.repository;

import com.blog.entity.BlogPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BlogPostRepositoryTest {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        BlogPost blogPost = BlogPost.builder()
                .title("NO")
                .content("hehe")
                .star(true)
                .build();
        entityManager.persist(blogPost);
        blogPostRepository.save(blogPost);

    }

    @Test
    public void whenFindByIdThenReturnBlogPostTitle() {
        BlogPost blogPost = blogPostRepository.findById(1L).get();
        assertEquals(blogPost.getTitle(), "NO");
    }

    @Test
    void whenFindByIdThenReturnBlogPostStar() {
        BlogPost blogPost = blogPostRepository.findById(1L).get();
        assertEquals(blogPost.getStar(), Boolean.TRUE);
    }


//    @Test
//    public void shouldNotFoundStar() {
//        BlogPost blogPost = (BlogPost) blogPostRepository.findByStar(Boolean.TRUE);
//
//        assertTrue(blogPosts.getStar(true));
//    }
}

