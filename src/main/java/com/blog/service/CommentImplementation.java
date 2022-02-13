package com.blog.service;

import com.blog.entity.Comment;
import com.blog.repository.BlogPostRepository;
import com.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentImplementation implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final BlogPostRepository blogPostRepository;

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> fetchCommentListByPostId(Long postId) {
        return commentRepository.getCommentsListByPostId(postId);
    }

    @Override
    public Comment fetchCommentByCommentId(Long commentId) {
        commentRepository.findById(commentId).isPresent();
        return commentRepository.findById(commentId).get();
    }
}
