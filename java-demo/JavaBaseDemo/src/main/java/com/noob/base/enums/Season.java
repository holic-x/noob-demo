package com.noob.base.enums;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 14:19
 */
public class Season {

    public String name;
    public String descr;

    Season(String name, String descr) {
        this.name = name;
        this.descr = descr;
    }

    public static void main(String[] args) {
        Season spring = new Season("spring", "春天来了");
        Season summer = new Season("summer", "夏天好热");
        Season autumn = new Season("autumn", "秋分凉爽");
        Season winter = new Season("winter", "一夜入冬");
    }
}
