package com.noob.base.controller;

import com.noob.base.mapper.StudentMapper;
import com.noob.base.mapper.UserMapper;
import com.noob.base.model.Student;
import com.noob.base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camps")
public class StudentController {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/addStudent")
    public String add(@RequestParam("name") String name, @RequestParam("createTime") String createTime, @RequestParam("gradeCode") String gradeCode) {
        Student student = new Student()
                .withName(name)
                .withCreateTime(createTime)
                .withGradeCode(gradeCode);
        studentMapper.insert(student);
        return "success";
    }

    @RequestMapping("/queryStudentById")
    public Student queryStudentByTime(@RequestParam("id") long id) {
        Student student = studentMapper.selectById(id);
        return student;
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestParam("name") String name, @RequestParam("createTime") String createTime, @RequestParam("age") int age) {
        User user = new User()
                .withName(name)
                .withCreateTime(createTime)
                .withAge(age);
        userMapper.insert(user);
        return "success";
    }

}
