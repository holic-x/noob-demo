package com.noob.framework.di.circle;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthorService {
    @Resource
    public BookService bookService;
}
