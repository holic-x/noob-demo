package com.noob.base.dataMasking.interceptor;

import com.noob.base.dataMasking.anno.DataMasking;
import com.noob.base.dataMasking.util.DataMaskingUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class DataMaskingInterceptor {

    public Object maskData(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(DataMasking.class)) {
                field.setAccessible(true);
                DataMasking dataMasking = field.getAnnotation(DataMasking.class);
                String originalValue = (String) field.get(object);
                String maskedValue = null;

                switch (dataMasking.maskType()) {
                    case PHONE:
                        maskedValue = DataMaskingUtil.maskPhoneNumber(originalValue);
                        break;
                    case EMAIL:
                        maskedValue = DataMaskingUtil.maskEmail(originalValue);
                        break;
                    case ID_CARD:
                        maskedValue = DataMaskingUtil.maskIdCard(originalValue);
                        break;
                }

                field.set(object, maskedValue);
            }
        }

        return object;
    }
}