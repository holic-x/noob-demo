package com.noob.framework.di.circle;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService {
    @Resource
    public AuthorService authorService;
}
