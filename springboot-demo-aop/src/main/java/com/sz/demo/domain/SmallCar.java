package com.sz.demo.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SmallCar {

    private String color;


    private String name;

//    @SysOperLogAnno(title = "111111", businessType = SysOperLogBusinessTypeEnum.EDIT, operationDesc = "编辑管理员账号")

    public void testAop(){
        System.out.print("测试ing......");
    }
}
