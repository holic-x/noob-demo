package com.noob.base.enums;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 14:42
 */
public enum SeasonEnum {
    /**
     * 定义一组固定对象（原public final static CustomSeasonEum SPRING 可以直接简化为SPRING）
     * 且这组枚举数据需定义在最前面，多个枚举数据定义用逗号，进行隔离
     */
    SPRING("SPRING","春天"),
    SUMMER("SUMMER","夏天"),
    AUTUMN("AUTUMN","秋天"),
    WINTER("WINTER","冬天");

    private String name;
    private String descr;


    // 构造私有化，避免new操作
    SeasonEnum(){}
    private SeasonEnum(String name, String descr) {
        this.name = name;
        this.descr = descr;
    }

    // 隐藏setter、提供getter


    public String getName() {
        return name;
    }

    public String getDescr() {
        return descr;
    }

    public static void main(String[] args) {
        System.out.println(SeasonEnum.SPRING.getDescr());
        System.out.println(SeasonEnum.SUMMER.getDescr());
        System.out.println(SeasonEnum.AUTUMN.getDescr());
        System.out.println(SeasonEnum.WINTER.getDescr());
    }

}
