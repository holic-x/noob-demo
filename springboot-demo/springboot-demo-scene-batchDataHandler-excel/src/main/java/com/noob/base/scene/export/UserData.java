package com.noob.base.scene.export;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模拟导出用户数据
 */
@Data
public class UserData {
    private String name;
    private String email;
    private String phone;

    public UserData(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
