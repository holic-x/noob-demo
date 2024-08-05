package com.noob.base.druid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.noob.base.druid.model.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
