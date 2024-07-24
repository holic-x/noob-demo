package com.noob.base;

import com.noob.base.utils.ClassUtil;
import com.noob.base.utils.EntityVoTestUtil;
import com.noob.base.utils.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class MainAppTest {

    /**
     * 部分情况无法完全覆盖
     */
    @Test
    void main() {
        try {
            List<Class<?>> classes = ClassUtil.getClasses("com.noob.base.model.entity");
            classes.forEach(cls->{
                try {
                    TestUtil.TestPojo(cls);
                } catch (IllegalAccessException e) {
                } catch (InstantiationException e) {
                }
            });

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存在不足（Lombok构建的实体无法覆盖）
     */
    @SneakyThrows
    @Test
    void testEntityVoTestUtil(){
        List<Class<?>> classes = ClassUtil.getClasses("com.noob.base.model.entity");
        EntityVoTestUtil.justRun(classes);
    }
}