package com.jsonObjTransfer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONtoJavaTest {

    public void javaObjToJSONstr() {
        Student student = new Student(18, "hh");
        JSONObject jsonObject = JSONObject.fromObject(student);
        System.out.println("javaObjToJSONstr执行结果:" + jsonObject.toString());
    }

    public void javaArrToJSONstr() {
        List<Student> studentList = new ArrayList<>();
        Student s1 = new Student(20, "小红");
        Student s2 = new Student(10, "小柏");
        Student s3 = new Student(30, "小张");
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        JSONArray jsonArr = JSONArray.fromObject(studentList);
        System.out.println("javaArrToJSONstr执行结果:" + jsonArr.toString());
    }


    public void jsonStrTojavaObj(String jsonstr) {
        // 将str字符串转化为JSONObject类型
        JSONObject js = JSONObject.fromObject(jsonstr);
        // 将json字符串转化为对象
        Student stu = (Student) JSONObject.toBean(js, Student.class);
        System.out.println("jsonStrTojavaObj执行结果:" + stu.toString());
    }

    public void jsonStrTojavaArray(String jsonstr) {

        JSONArray js = JSONArray.fromObject(jsonstr);
//        System.out.println("jsonStrTojavaArray执行结果:");
//        Student[] students = (Student[]) JSONArray.toArray(js);
//        for (Student stu : students) {
//            System.out.println(stu.toString());
//        }
        Object obj = JSONArray.toArray(js);
        System.out.println("jsonStrTojavaArray执行结果:" + obj.toString());
        System.out.println(Arrays.asList((Object[]) obj));
    }


    public static void main(String[] args) {

        // 测试
        JSONtoJavaTest jtest = new JSONtoJavaTest();
        jtest.javaObjToJSONstr();
        jtest.javaArrToJSONstr();
        jtest.jsonStrTojavaObj("{'name':'方依依','age':17}");
//        jtest.jsonStrTojavaObj("{\"age\":18,\"name\":\"hh\",\"stuNo\":\"\"}");
        jtest.jsonStrTojavaArray("[{'stuNo':'20170031','name':'何立立'},{'stuNo':'20170032','name':'赵多多'},{'stuNo':'20170033','name':'钱少少'}]");

    }
}
