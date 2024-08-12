package com.noob.base.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.noob.base.demo.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper 定义数据库操作方法
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
