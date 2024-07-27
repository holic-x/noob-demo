package com.noob.base.model.entity.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder // builder 方式构建待覆盖（链式构建和传统无参、带参可能有冲突需处理，注意注解组合使用的限制）
@Data
@NoArgsConstructor // 如果覆盖率为0 需关注是否通过无参或者有参构造函数进行覆盖，工具类默认是通过构造函数创建对象，如果不提供则对象创建失败
public class Student {

    private int id;
    private String name;
    private int age;

}
