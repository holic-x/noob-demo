package com.noob.base.annoation;

// 查询条件
class Filter{
    private String userId;
    private String userName;
    private String nickName;
    private int age;
    private int sex;
    private String city;
    private String email;
    private String phone;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}



public class CustomSQLAnnotationDemo {

    public static void main(String[] args) {
        Filter f1 = new Filter();
        f1.setUserId("1");// 查询id为10的用户


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

    private static String query(Filter f1) {
        return null;
    }
}
