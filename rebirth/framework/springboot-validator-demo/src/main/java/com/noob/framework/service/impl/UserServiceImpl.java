package com.noob.framework.service.impl;

import com.noob.framework.exception.BusinessException;
import com.noob.framework.constant.Constant;
import com.noob.framework.model.User;
import com.noob.framework.model.UserAddDTO;
import com.noob.framework.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService {
    @Override
    public void save(User user) {
        // 串行校验（逐个手动校验，可以设定不同的处理方式）
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String phone = user.getPhone();

        // 校验用户名
        if(StringUtils.isEmpty(username)){
            // 处理1：抛出自定义异常
            throw new BusinessException(Constant.PARAM_FAIL_ERROR,"用户名不能为空");
        }

        // 校验密码
        if(StringUtils.isEmpty(password)){
            // 处理2：定义一个Map存放异常信息，并将Map返回
            Map<String,Object> map = new HashMap<>();
            map.put("code",Constant.PARAM_FAIL_ERROR);
            map.put("msg","密码不能为空");
        }

        // 校验邮箱
        if(!StringUtils.isEmpty(email)){
            // 如果传入邮箱，则校验邮箱有效性
            if(!email.contains("@")){
                throw new BusinessException(Constant.PARAM_FAIL_ERROR,"【邮箱】格式校验错误");
            }
        }

        // 校验手机号
        if(!StringUtils.isEmpty(phone)){
            // 如果传入手机号，则校验手机号有效性
            if(!Pattern.matches("^[1][3,4,5,6,7,8,9][0-9]{9}$", phone)){
                throw new BusinessException(Constant.PARAM_FAIL_ERROR,"【联系方式】格式校验错误");
            }
        }

        // 校验通过，模拟添加用户操作
        System.out.println("数据校验通过，模拟用户添加操作");
    }

    @Override
    public void saveByValidator(UserAddDTO userAddDTO) {
        // 模拟添加用户操作
        System.out.println("数据校验通过，模拟用户添加操作");
    }
}
