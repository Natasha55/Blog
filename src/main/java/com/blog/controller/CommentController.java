package com.blog.controller;

import com.blog.entity.Comment;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    public CommentController() {
    }

    @PostMapping("/{id}/comments")
    public Comment saveComment(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        comment.setPostId(id);
        return commentService.saveComment(comment);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> fetchCommentListById(@PathVariable Long id) {
        return commentService.fetchCommentListByPostId(id);
    }

    @GetMapping("/comments/{commentId}")
    public Comment fetchCommentByCommentId(@PathVariable Long commentId) {
        return commentService.fetchCommentByCommentId(commentId);
    }

}
