package com.noob.base.mock;

import com.noob.base.demo.mapper.UserMapper;
import com.noob.base.demo.model.entity.User;
import com.noob.base.demo.service.UserService;
import com.noob.base.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Mock UserService Test
 */
@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Test
    void testAdd()
    {
        User user = Mockito.any(User.class);
        // 测试添加
//        Mockito.when(userMapper.insert(Mockito.any(User.class))).thenReturn(1);
        Mockito.when(userMapper.insert(user)).thenReturn(1);
        userService.save(user);
//        Mockito.verify(userMapper, Mockito.times(1)).insert(Mockito.any(User.class));
    }

    @Test
    void testUpdate()
    {
        User user = new User("noob",18);
        user.setId(1);
        // 测试修改
        Mockito.when(userMapper.updateById(user)).thenReturn(1);
    }

    @Test
    void testGet()
    {
        userService.testService("啊哈哈");
    }
}