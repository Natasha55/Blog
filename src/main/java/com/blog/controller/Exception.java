package com.blog.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such post")
public class Exception extends RuntimeException {

    public Exception(Long postId) {

    }
}
