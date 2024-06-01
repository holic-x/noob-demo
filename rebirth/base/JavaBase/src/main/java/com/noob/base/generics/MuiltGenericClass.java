package com.noob.base.generics;

class MyMap<K,V> {
    private K key;
    private V value;
    public MyMap(K key, V value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public String toString() {
        return "MyMap{" + "key=" + key + ", value=" + value + '}';
    }
}

/**
 * 多类型参数的泛型类定义
 */
public class MuiltGenericClass {
    public static void main(String[] args) {
        MyMap<Integer, String> map = new MyMap<>(1, "one");
        System.out.println(map);
    }
}
