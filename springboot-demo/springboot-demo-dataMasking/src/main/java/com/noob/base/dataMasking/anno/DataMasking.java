package com.noob.base.dataMasking.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解（用于标记需要脱敏的字段）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataMasking {
    MaskType maskType() default MaskType.PHONE;

    enum MaskType {
        PHONE,
        EMAIL,
        ID_CARD
    }
}