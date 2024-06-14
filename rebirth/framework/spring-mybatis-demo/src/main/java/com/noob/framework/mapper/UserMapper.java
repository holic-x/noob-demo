package com.noob.framework.mapper;

import com.noob.framework.model.User;
import org.apache.ibatis.annotations.Mapper;

public interface UserMapper {
    void findUserById(String id);
}
