package com.noob.annotation;

import com.noob.annotation.mapper.UserMapper;
import com.noob.annotation.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/8/9
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class UserMapperTest {

    // 优化测试，将公共代码提取出来
    SqlSessionFactory sqlSessionFactory;

    @Before // 在所有测试代码执行之前执行该方法
    public void before() throws Exception {
        // a.定义mybatis配置文件,并得到配置文件的流
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // b.根据流创建会话工厂,传入mybatis配置信息到会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession对象获取UserMapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // ------- 业务测试 -------
        User user = new User();
        user.setId(1001);
        user.setUsername("test");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("拉斯维加斯");
        // 新增用户
        userMapper.insert(user);
        // 提交事务，关闭连接
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testUpdate() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession对象获取UserMapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // ------- 业务测试 -------
        User updateUser = new User();
        updateUser.setId(1001);
        updateUser.setUsername("haha");
        updateUser.setAddress("china");
        userMapper.update(updateUser);

        // 提交事务，关闭连接
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession对象获取UserMapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // ------- 业务测试 -------
        userMapper.delete(1001);
        // 提交事务，关闭连接
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession对象获取UserMapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // ------- 业务测试 -------
        List<User> list = userMapper.selectAll();
        list.forEach(System.out::println);
        // 关闭连接
        sqlSession.close();
    }


    @Test
    public void testSelectById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession对象获取UserMapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // ------- 业务测试 -------
        User findUser = userMapper.selectById(1001);
        System.out.println(findUser.getId()+"-"+findUser.getUsername());
        // 关闭连接
        sqlSession.close();
    }

}
