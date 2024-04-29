package com.noob.base.enums;

import java.sql.SQLOutput;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 14:29
 */
public class CustomSeasonEnum {

    public static void main(String[] args) {
        System.out.println(CustomSeasonEnum.SPRING.getDecsr());
        System.out.println(CustomSeasonEnum.SUMMER.getDecsr());
        System.out.println(CustomSeasonEnum.AUTUMN.getDecsr());
        System.out.println(CustomSeasonEnum.WINTER.getDecsr());
    }

    // 属性定义
    private String name;
    private String decsr;

    // 构造函数私有化(防止直接new)
    private CustomSeasonEnum(String aName, String aDecsr) {
        this.name = aName;
        this.decsr = aDecsr;
    }

    // 去掉setter（枚举通常为只读），仅提供getter
    public String getName() {
        return name;
    }

    public String getDecsr() {
        return decsr;
    }

    // 在Season内部直接创建固定的对象(对象用大写常量规范定义)，使用final static 修饰 优化底层实现
    public static final CustomSeasonEnum SPRING = new CustomSeasonEnum("SPRING","春天");
    public static final CustomSeasonEnum SUMMER = new CustomSeasonEnum("SUMMER","夏天");
    public static final CustomSeasonEnum AUTUMN = new CustomSeasonEnum("AUTUMN","秋天");
    public static final CustomSeasonEnum WINTER = new CustomSeasonEnum("WINTER","冬天");
}
