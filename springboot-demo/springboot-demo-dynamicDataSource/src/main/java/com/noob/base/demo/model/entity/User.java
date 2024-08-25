package com.noob.base.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {

    @TableId("user_id")
    private Integer userId;

    @TableField("user_name")
    private String userName;

    @TableField("nick_name")
    private String nickName;

    @TableField("email")
    private String email;

    @TableField("password")
    private String password;

}
