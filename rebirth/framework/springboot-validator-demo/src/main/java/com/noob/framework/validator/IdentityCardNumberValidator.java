package com.noob.framework.validator;

import com.noob.framework.constant.Constant;
import com.noob.framework.exception.BusinessException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentityCardNumberValidator implements ConstraintValidator<IdentityCardNumber, Object> {

    @Override
    public void initialize(IdentityCardNumber identityCardNumber) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        // 空指针异常处理
        if(o==null){
            // 如果传入o为空，则不做任何校验，默认放行
            return true;
        }
        // 如果传入o不为空，则进行业务校验
        return IdCardValidatorUtils.isValidate18Idcard(o.toString());
    }
}