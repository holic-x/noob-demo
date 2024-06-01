package com.noob.base.annoation;

// 自定义注解@Table

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 自定义注解@Table
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}


// 自定义注解@Cloumn
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Column{
    String value();
}

// 查询条件
@Table("user")
class Filter{
    @Column("user_id")
    private int userId;
    @Column("user_name")
    private String userName;
    @Column("nick_name")
    private String nickName;
    @Column("age")
    private int age;
    @Column("sex")
    private int sex;
    @Column("city")
    private String city;
    @Column("email")
    private String email;
    @Column("phone")
    private String phone;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

public class CustomSQLAnnotationDemo {

    public static void main(String[] args) throws Exception {
        Filter f1 = new Filter();
        f1.setUserId(1);// 查询id为10的用户

        Filter f2 = new Filter();
        f2.setUserName("lucy");// 模糊查询用户名为lucy的用户

        Filter f3 = new Filter();
        f1.setEmail("lucy@163.com,xiaobai@126.com");// 查询邮箱为任意一个的用户信息

        // 根据上述条件，生成相应的sql语句
        String sql1 = query(f1);
        String sql2 = query(f2);
        String sql3 = query(f3);
        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);

    }

    /**
     * query 方法：解析注解生成sql语句
     * @param f
     * @return
     */
    private static String query(Filter f) throws Exception {
        // 定义字符串对象封装sql
        StringBuilder sb = new StringBuilder();
        String tableName = "";

        // 获取到class
         Class c = f.getClass();
         // 根据class获取到相关注解
        boolean isExistTable = c.isAnnotationPresent(Table.class);
        if(isExistTable){
            // 获取到tableName
            Table tableAnno = (Table)c.getAnnotation(Table.class);
            tableName = tableAnno.value();
        }
        // 拼接检索语句
        sb.append("select * from " + tableName).append(" where 1=1 ");

        // 获取所有字段上的注解内容
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            boolean isExistFieldAnno = field.isAnnotationPresent(Column.class);
            if(isExistFieldAnno){
                Column columnAnno = (Column)field.getAnnotation(Column.class);
                // 通过反射获取到字段值（因为方法名有规律，可以通过字段名获取到方法名）
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method getMethod = c.getDeclaredMethod(getMethodName,null);
                // 调用方法获取到字段值
                 Object fieldValue = getMethod.invoke(f,null);
                 // 处理不同字段类型转换、以及null字段值处理（String类型为null则不拼接、Integer类型为0也不拼接）
                if(null == fieldValue || (fieldValue instanceof Integer && (Integer)fieldValue==0)){
                    continue;
                }

                if(fieldValue instanceof String){
                    if(((String) fieldValue).contains(",")){
                        // 对于String类型的列表数据（通过,进行分割）,将其解析为in形式
                        sb.append("and ").append(fieldName).append(" in (");
                        String[] strs = fieldValue.toString().split(",");
                        for (String str : strs) {
                            sb.append("'" + str + "',");
                        }
                        // 删除最后一个逗号（,）
                        sb.deleteCharAt(sb.length()-1);
                        sb.append(")");
                    }else{
                        // 如果是String类型，则需要拼接‘’
                        sb.append("and " + fieldName + "='" + fieldValue + "' ");
                    }
                }else if(fieldValue instanceof Integer){
                    // 如果是Integer类型，则直接拼接
                    sb.append("and " + fieldName + "=" + fieldValue + " ");
                }
            }
        }
        return sb.toString();
    }
}