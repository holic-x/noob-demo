package com.noob.base.coverage.mockEntity;

/**
 * 特殊实体定义
 */
public class SpecialEntity {

    private String name = "111";

    // toString 方法没有类名、也不包含字段名(值)
    @Override
    public String toString() {
        return "HHH{" +
                "xixixii='" + name + '\'' +
                '}';
    }
}
