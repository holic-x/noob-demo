package com.noob.base.annoation;


import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * 自定义注解@Description
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@interface Description{

    String desc();

    String author();

    int age() default 18;

}

@Description(desc = "用户类",author = "holic-x",age = 18)
class User{
    private String name;


    @Description(desc = "getName方法",author = "noob",age = 33)
    public String getName() {
        return name;
    }
}

// 定义注解解析类(通过反射机制)
class ParseDescriptionAnno{

    // 获取类上的注解
     public static void getClassAnno(){
         // 1.使用类加载器加载类
         Class clazz = User.class;
         // 2.找到类上面的注解
         boolean isExistAnno = clazz.isAnnotationPresent(Description.class);
         // 3.获取到注解示例
         if(isExistAnno){
             Description d = (Description) clazz.getAnnotation(Description.class);
             System.out.println(d.desc());
         }
     }

     // 获取方法上的注解
    public static void getMethodAnno(){
         Method[] methods = User.class.getMethods();
         for(Method method : methods){
             if(method.isAnnotationPresent(Description.class)){
                 Description d = (Description) method.getAnnotation(Description.class);
                 System.out.println(d.desc());
             }
         }
    }

    // 获取方法上的注解（另一种方式获取）
    public static void getMethodAnnoByOther(){
        Method[] methods = User.class.getMethods();
        for(Method method : methods){
            // 获取方法上的所有注解
            Annotation[] annotations = method.getDeclaredAnnotations();
            for(Annotation annotation : annotations){
                if(annotation instanceof Description){
                    Description d = (Description) annotation;
                    System.out.println(d.desc());
                }
            }
        }
    }

    public static void main(String[] args) {
        getClassAnno();
        getMethodAnno();
        getMethodAnnoByOther();
    }

}










public class CustomAnnotationDemo {
}
