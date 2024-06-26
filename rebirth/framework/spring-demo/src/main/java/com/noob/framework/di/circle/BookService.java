package com.noob.framework.di.circle;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
public class BookService {

    @Lazy
    @Resource
    public AuthorService authorService;

//    public BookService(@Lazy AuthorService authorService) {
//        this.authorService = authorService;
//    }
}




