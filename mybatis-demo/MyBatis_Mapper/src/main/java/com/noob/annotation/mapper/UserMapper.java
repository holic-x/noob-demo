package com.noob.annotation.mapper;

import com.noob.annotation.model.User;
import com.noob.annotation.provider.BaseUserProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/8/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public interface UserMapper {

    //新增操作
    @Insert("INSERT INTO user(id,username,birthday,sex,address) VALUES (#{id},#{username},#{birthday},#{sex},#{address})")
    public abstract Integer insert(User user);

    //修改操作
    @Update("UPDATE user SET username=#{username},birthday=#{birthday},sex=#{sex},address=#{address} WHERE id=#{id}")
    public abstract Integer update(User user);

    //删除操作
    @Delete("DELETE FROM user WHERE id=#{id}")
    public abstract Integer delete(Integer id);

    //查询全部
    @Select("SELECT * FROM user")
    public abstract List<User> selectAll();

    // 根据ID查找用户信息
    @SelectProvider(type = BaseUserProvider.class,method = "selectById")
    public abstract User selectById(@Param(value = "id")Integer id);

}
