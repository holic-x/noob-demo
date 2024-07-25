package com.noob.base.model.entity.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor // 如果覆盖率为0 需关注是否通过无参或者有参构造函数进行覆盖，工具类默认是通过构造函数创建对象，如果不提供则对象创建失败
public class Student {

    private int id;
    private String name;
    private int age;

}
