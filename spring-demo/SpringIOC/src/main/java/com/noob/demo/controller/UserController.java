package com.noob.demo.controller;


import com.noob.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    /*
    使用@Autowired来实现自动注入
    bytype?  byname?
    ·默认优先根据类型去匹配
    ·如果匹配到多个类型则会按照名字匹配
    ·如果名又没有匹配到则会报错：
                    1.可以去修改属性的名字对应bean的名字：userServiceImpl
                    2.可以去修改Bean的名字对应属性的名字：@Service("userService")
                    3.可以通过@Qualifier设置属性的名字（覆盖） :@Qualifier("userServiceImpl")
                    4.可以通过@Primary 设置其中一个Bean为主要的自动注入Bean：@Primary
                    5.使用泛型作为自动注入限定符
     */

    /**
     * @Autowired和@Resource区别
     * @Resource 依赖jdk  @Autowired依赖spring
     * @Resource 优先根据名字匹配
     * @Autowired 优先根据类型匹配
     */
    @Autowired
    //@Qualifier("userServiceImpl")
            //@Resource
            UserService userService;


    /**
     * @Autowired 也可以写在构造器上面
     * ·默认优先根据参数类型去匹配
     * ·如果匹配到多个类型则会按照参数名字匹配
     @Autowired public UserController(UserService userService) {
     this.userService = userService;
     }*/

    /**
     * @param userService
     * @Autowired 也可以写在方法上面
     * ·默认优先根据参数类型去匹配
     * ·如果匹配到多个类型则会按照参数名字匹配
     * @Autowired public void createUserSerive(@Qualifier("userServiceImpl")UserService userService){
     * this.userService=userService;
     * }
     */


    public void getUser() {
        userService.getBean();
    }
}
