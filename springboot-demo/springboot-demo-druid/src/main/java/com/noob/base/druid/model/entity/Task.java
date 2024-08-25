package com.noob.base.druid.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("t_task_test_druid")
@Data
public class Task {

//    @TableId(type = IdType.ASSIGN_UUID)
//    @TableId(type = IdType.ASSIGN_ID)
    @TableId(type = IdType.AUTO)
    private String id;

    @TableField("task_id")
    private String taskId;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

}
