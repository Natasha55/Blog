package com.blog.service;

import com.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);

    List<Comment> fetchCommentListByPostId(Long id);

    Comment fetchCommentByCommentId(Long commentId);
}
