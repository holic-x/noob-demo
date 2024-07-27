package com.noob.base.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.noob.base.demo.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
