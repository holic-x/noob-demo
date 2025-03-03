package com.noob.base.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users") // 指定表名
public class User {
    @TableId // 主键注解
    private Long id;
    private String username;
    private String email;
}