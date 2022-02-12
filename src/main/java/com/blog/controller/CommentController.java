package com.blog.controller;

import com.blog.entity.BlogPost;
import com.blog.service.BlogPostService;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    public CommentController() {
    }



    }

