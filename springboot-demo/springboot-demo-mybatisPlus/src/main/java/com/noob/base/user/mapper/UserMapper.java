package com.noob.base.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.noob.base.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // MyBatis-Plus 已经提供了基础的 CRUD 方法，无需手动编写
}