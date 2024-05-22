package com.noob.base.annoation;


import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 定义自定义注解RegexValid
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface RegexValid{

    enum Policy {
        // @formatter:off
        EMPTY(null),
        DATE("^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1"
                + "(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|"
                + "(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\\2(?:29))$"),
        MAIL("^[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})$");
        // @formatter:on

        private String policy;

        Policy(String policy) {
            this.policy = policy;
        }

        public String getPolicy() {
            return policy;
        }
    }

    String value() default "";
    Policy policy() default Policy.EMPTY;

}

// 定义注解解析器（解析使用了该注解的内容）
class RegexValidUtil{
    public static boolean check(Object obj) throws Exception {
        boolean result = true;
        StringBuilder sb = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 判断成员是否被 @RegexValid 注解所修饰
            if (field.isAnnotationPresent(RegexValid.class)) {
                RegexValid valid = field.getAnnotation(RegexValid.class);

                // 如果 value 为空字符串，说明没有注入自定义正则表达式，改用 policy 属性
                String value = valid.value();
                if ("".equals(value)) {
                    RegexValid.Policy policy = valid.policy();
                    value = policy.getPolicy();
                }

                // 通过设置 setAccessible(true) 来访问私有成员
                field.setAccessible(true);
                Object fieldObj = null;
                try {
                    fieldObj = field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (fieldObj == null) {
                    sb.append("\n")
                            .append(String.format("%s 类中的 %s 字段不能为空！", obj.getClass().getName(), field.getName()));
                    result = false;
                } else {
                    if (fieldObj instanceof String) {
                        String text = (String) fieldObj;
                        Pattern p = Pattern.compile(value);
                        Matcher m = p.matcher(text);
                        result = m.matches();
                        if (!result) {
                            sb.append("\n").append(String.format("%s 不是合法的 %s ！", text, field.getName()));
                        }
                    } else {
                        sb.append("\n").append(
                                String.format("%s 类中的 %s 字段不是字符串类型，不能使用此注解校验！", obj.getClass().getName(), field.getName()));
                        result = false;
                    }
                }
            }
        }

        if (sb.length() > 0) {
            throw new Exception(sb.toString());
        }
        return result;
    }
}


// 定义类使用自定义注解校验参数
class Person{

    private String name;
    @RegexValid(policy = RegexValid.Policy.DATE)
    private String date;
    @RegexValid(policy = RegexValid.Policy.MAIL)
    private String mail;
    @RegexValid("^((\\+)?86\\s*)?((13[0-9])|(15([0-3]|[5-9]))|(18[0,2,5-9]))\\d{8}$")
    private String phone;

    public Person(String name, String date, String mail, String phone) {
        this.name = name;
        this.date = date;
        this.mail = mail;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", date='" + date + '\'' + ", mail='" + mail + '\'' + ", phone='"
                + phone + '\'' + '}';
    }

}



/**
 * 自定义注解应用：参数校验器
 */
public class RegexValidDemo {

    // 方法上使用自定义注解RegexValid对日期进行校验
     static void printDate(@RegexValid(policy = RegexValid.Policy.DATE) String date){
         System.out.println(date);
     }

    public static void main(String[] args) throws Exception {
        Person p1 = new Person("Tom", "1990-01-31", "xxx@163.com", "18612341234");
        Person p2 = new Person("Jack", "2019-02-29", "sadhgs", "183xxxxxxxx");
        if (RegexValidUtil.check(p1)) {
            System.out.println(p1 + "正则校验通过");
        }
        if (RegexValidUtil.check(p2)) {
            System.out.println(p2 + "正则校验通过");
        }
    }
}
