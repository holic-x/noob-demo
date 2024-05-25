package com.noob.collection;

import java.util.*;

// Comparable排序
class Person implements Comparable<Person>{
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 重写compareTo方法实现按照年龄排序
    @Override
    public int compareTo(Person o) {
        if (this.age > o.getAge()) {
            return 1;
        }
        if (this.age < o.getAge()) {
            return -1;
        }
        return 0;
    }
}


/**
 * 排序 demo
 */
public class SortDemo {

    public static void main(String[] args) {

        // 1.Comparable验证
        TreeMap<Person,String> persons = new TreeMap<Person,String>();
        persons.put(new Person("John", 27),"John");
        persons.put(new Person("Jane", 18),"Jane");
        persons.put(new Person("Jack", 6),"Jack");
        persons.put(new Person("Jill", 34),"Jill");
        Set<Person> keys = persons.keySet();
        for (Person key : keys) {
            System.out.println(key.getName() + " " + key.getAge());
        }
        System.out.println("----------------------------------------------------------");

        // 2.Comparator验证(Comparator定制排序)
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        System.out.println("原始数组:" + arrayList);
        Collections.reverse(arrayList);// void reverse(List list)：反转
        System.out.println("Collections.reverse(arrayList):"+ arrayList);

        Collections.sort(arrayList); // void sort(List list),按自然排序的升序排序
        System.out.println("Collections.sort(arrayList):" + arrayList);
        // 定制排序的用法
        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("定制排序后：" + arrayList);
    }
}
