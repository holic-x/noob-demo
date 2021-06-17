package com.sz.demo;

import com.sz.demo.aop.SpringUtil;
import com.sz.demo.domain.SmallCar;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lmb on 2018/9/5.
 */
@RestController
@RequestMapping("/myAdvice")
public class MyAdviceController {

    @RequestMapping(value = "/go")
    public String go(){
        return "success";
    }

    @RequestMapping("/aop")
    public void aop(){
        SpringUtil.getBean(SmallCar.class).testAop();
    }

}