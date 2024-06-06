package com.noob.framework.di.annotation;

import org.springframework.stereotype.Repository;

// Bean注册（将数据访问层的PersonDaoImpl实现类标识为spring的bean）
@Repository("personDao")
public class PersonDaoImpl implements PersonDao{
    @Override
    public void save(String userName) {
        System.out.println("PersonDaoImpl save方法执行");
        System.out.println("mod save:" + userName);
    }
}
