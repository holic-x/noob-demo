package com.noob.base.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.noob.base.demo.entity.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // MyBatis-Plus 已经提供了基本的 CRUD 方法
    // 可以在这里添加自定义的查询方法
}