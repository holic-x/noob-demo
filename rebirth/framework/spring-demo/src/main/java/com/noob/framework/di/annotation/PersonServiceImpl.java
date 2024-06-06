package com.noob.framework.di.annotation;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

// bean注册：将service层（业务层）的PersonServiceImpl标记为spring的bean
@Service("personService")
public class PersonServiceImpl implements PersonService{

    @Resource(name = "personDao")
    private PersonDao personDao;

    @Override
    public void addUser(String userName) {
        System.out.println("PersonServiceImpl addUser方法执行");
        personDao.save(userName);
    }
}
