
package com.noob.base.order.model.entity;

import com.noob.base.order.model.enums.UserLevel;
import lombok.Getter;

/**
 * 用户类
 */
@Getter
public class User {
    private String userId;
    private UserLevel level;
    
    public User(String userId, UserLevel level) {
        this.userId = userId;
        this.level = level;
    }
}