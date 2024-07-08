package com.noob.base.batchHandle.entity.model;

import lombok.Data;

/**
 * 单表（s1、s2）- 用于大数据量模拟测试
 */
@Data
public class SingleTable {
    private int id;
    private String key1;
    private String key2;
    private String key_part1;
    private String key_part2;
    private String key_part3;
    private String common_field;
}
