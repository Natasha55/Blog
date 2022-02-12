package com.blog.service;

import com.blog.entity.BlogPost;
import com.blog.repository.BlogPostRepository;
import com.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentImplementation implements CommentService{

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final BlogPostRepository blogPostRepository;

}
