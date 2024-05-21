package com.noob.base.generics;

// 泛型接口定义
interface Content<T> {
    T text();
}

// 泛型接口实现1：子类明确声明泛型类型
class SubContent1<String> implements Content<String> {
    private String text;
    public SubContent1(String text) {
        this.text = text;
    }
    @Override
    public String text() {
        return text;
    }
}


// 泛型接口实现2：子类不明确声明泛型类型
class SubContent2<T> implements Content<T> {
    private T text;
    public SubContent2(T text) {
        this.text = text;
    }
    @Override
    public T text() {
        return text;
    }
}

/**
 * 泛型接口
 */
public class GenericInterface {
    public static void main(String[] args) {
        // 子类明确声明泛型类型
        SubContent1 subContent1 = new SubContent1("Hello");
        System.out.println(subContent1.text());

        // 子类不明确声明泛型类型
        SubContent2<Integer> subContent2 = new SubContent2<Integer>(10);
        System.out.println(subContent2.text());
    }
}
