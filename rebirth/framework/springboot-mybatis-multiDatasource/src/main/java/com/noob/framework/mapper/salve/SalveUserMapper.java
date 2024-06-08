package com.noob.framework.mapper.salve;

import com.noob.framework.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalveUserMapper {
    public List<User> getAllUser();
}
