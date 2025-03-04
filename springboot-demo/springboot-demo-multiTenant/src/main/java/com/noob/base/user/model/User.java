package com.noob.base.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("noob_user")
public class User {
    private Long id;
    private Long tenantId;
    private String name;
}