package com.blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blogPostId;
    private String blogPostTitle;
    private String blogPostContent;

    public Long getBlogPostId() {
        return blogPostId;
    }

    public BlogPost(Long blogPostId, String blogPostTitle, String blogPostContent) {
        this.blogPostId = blogPostId;
        this.blogPostTitle = blogPostTitle;
        this.blogPostContent = blogPostContent;
    }

    public BlogPost() {
    }

    public String getBlogPostContent() {
        return blogPostContent;
    }

    public void setBlogPostContent(String blogPostContent) {
        this.blogPostContent = blogPostContent;
    }

    public String getBlogPostTitle() {
        return blogPostTitle;
    }

    public void setBlogPostTitle(String blogPostTitle) {
        this.blogPostTitle = blogPostTitle;
    }
}
