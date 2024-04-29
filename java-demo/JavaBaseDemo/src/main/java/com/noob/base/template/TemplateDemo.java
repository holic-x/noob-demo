package com.noob.base.template;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 13:57
 */

public class TemplateDemo {

    public static void main(String[] args) {
        TaskA taskA = new TaskA();
        taskA.job();
        taskA.otherJob();
        TaskB taskB = new TaskB();
        taskB.job();
        TaskC taskC = new TaskC();
        taskC.job();
    }

}


// 1.定义抽象模板类
abstract class TaskTemplate{
    // 定义公共抽象方法
    public void job(){
        System.out.println("模板提供的工作方法");
    }
}

// 2.定义多个类继承TaskTemplate重写job实现自定义业务逻辑
class TaskA extends TaskTemplate{
    public void job(){
        // 模拟工作计时(System.currentTimeMillis()获取当前系统时间毫秒数)
        long duration = 100l;
        System.out.println("TaskA 耗时" + duration);
    }

    // 子类自定义其他业务逻辑操作
    public void otherJob(){
        System.out.println("接其他活中....");
    }
}

class TaskB extends TaskTemplate{
    public void job(){
        // 模拟工作计时(System.currentTimeMillis()获取当前系统时间毫秒数)
        long duration = 2000l;
        System.out.println("TaskB 耗时" + duration);
    }
}

class TaskC extends TaskTemplate{
    // TaskC使用父类的job，不自定义
}


