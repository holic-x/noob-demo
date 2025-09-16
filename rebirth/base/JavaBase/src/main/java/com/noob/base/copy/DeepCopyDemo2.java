package com.noob.base.copy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

@Data
@AllArgsConstructor
class Computer implements Serializable {
    private String name;
    private Cpu cpu;
}

@Data
@AllArgsConstructor
class Cpu implements Serializable {
    private String title;
}

/**
 * 深拷贝：序列化(借助SerializationUtils工具实现)
 */
public class DeepCopyDemo2 {

    public static void main(String[] args) {
        // 使用SerializationUtils进行深拷贝
        Computer c1 = new Computer("宏碁001", new Cpu("顶配"));
        Computer c2 = SerializationUtils.clone(c1);

        System.out.println(c1.getName().equals(c2.getName())); // true
        System.out.println(c1.getCpu() == c2.getCpu()); // false

        // 尝试修改引用对象的内容
        c1.getCpu().setTitle("辣鸡配置");
        System.out.println(c1.getCpu()); // 辣鸡配置
        System.out.println(c2.getCpu()); // 顶配
    }
}
