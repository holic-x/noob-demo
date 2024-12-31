package com.noob.rpc.proxy.javassist;

import javassist.*;

import java.lang.reflect.Method;

/**
 * Javassist demo 示例
 */
public class JavassistDemo1 {

    // 基于Javassist生成类（返回CtClass类型）
    public static CtClass generateClass(String savePath) throws Exception {
        // 创建ClassPool
        ClassPool cp = ClassPool.getDefault();

        // 指定生成的类名称
        CtClass clazz = cp.makeClass("com.noob.javassist.demo.JavassistTemplate");

        // 创建字段，指定字段基础属性：字段类型、字段名称、字段所属的类
        CtField field = new CtField(cp.get("java.lang.String"), "prop", clazz);
        field.setModifiers(Modifier.PRIVATE); // 指定该字段使用private修饰

        // 设置prop字段的getter/setter方法
        clazz.addMethod(CtNewMethod.setter("setProp", field));
        clazz.addMethod(CtNewMethod.getter("getProp", field));

        // 设置prop字段的初始化值，并将prop字段添加到clazz中
        clazz.addField(field, CtField.Initializer.constant("hello world"));

        // 创建构造方法，指定了构造方法的参数类型和构造方法所属的类
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, clazz);

        // 设置方法体
        StringBuffer body = new StringBuffer();
        body.append("{\n prop=\"dididi\";\n}");
        ctConstructor.setBody(body.toString());
        clazz.addConstructor(ctConstructor); // 将构造方法添加到clazz中

        // 创建execute()方法，指定了方法返回值、方法名称、方法参数列表以及方法所属的类
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "execute", new CtClass[]{}, clazz);
        ctMethod.setModifiers(Modifier.PUBLIC); // 指定该方法使用public修饰

        // 设置方法体
        body = new StringBuffer(); // reset
        body.append("{\n System.out.println(\"execute():\" " + "+ this.prop);").append("\n}"); // 填充方法体内容
        ctMethod.setBody(body.toString()); // 设置方法体
        clazz.addMethod(ctMethod); // 将execute()方法添加到clazz中

        // 将定义好的JavassistDemo类保存到指定的目录
        clazz.writeFile(savePath);  // /Users/xxx/

        // 返回CtClass对象
        return clazz;
    }

    // 测试基于Javassist生成类
    public static void testJavassist(CtClass clazz) throws Exception {
        // 加载clazz类，并创建对象
        Class<?> c = clazz.toClass();
        Object o = c.newInstance();

        // 调用execute()方法
        Method method = o.getClass().getMethod("execute", new Class[]{});
        method.invoke(o, new Object[]{});
    }

    public static void main(String[] args) throws Exception {
        CtClass clazz = generateClass("D:\\");
        testJavassist(clazz);
    }
}
