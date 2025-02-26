package com.noob.base.largeData.service;

import com.noob.base.largeData.dao.UserRepository;
import com.noob.base.largeData.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 批量插入用户数据
    public void saveBatch(List<User> users) {
        userRepository.saveAll(users);
    }

    // 分页查询用户数据
    public List<User> getUsersByPage(int pageNum, int pageSize) {
        return userRepository.findAllByOrderByIdAsc();
    }
}