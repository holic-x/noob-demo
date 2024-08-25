package com.noob.base.druid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noob.base.druid.mapper.TaskMapper;
import com.noob.base.druid.model.entity.Task;
import com.noob.base.druid.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
}
