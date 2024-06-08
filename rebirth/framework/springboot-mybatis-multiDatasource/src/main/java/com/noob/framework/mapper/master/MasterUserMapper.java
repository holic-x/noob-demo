package com.noob.framework.mapper.master;

import com.noob.framework.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MasterUserMapper {
    public List<User> getAllUser();
}
