package com.noob.base.demo.entity.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("db_noob_user") // DB 数据表名映射
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String city;

    private String email;

    @TableField(value = "create_time", fill = FieldFill.INSERT) // 可自定义mybatisplus的handler补充字段插入的填充逻辑
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE) // 可自定义mybatisplus的handler补充字段插入的填充逻辑
    private LocalDateTime updateTime;

    // @TableLogic
    private Integer isDeleted;
}