package com.noob.base;

import com.noob.base.utils.ClassUtil;
import com.noob.base.utils.EntityVoTestUtil;
import com.noob.base.utils.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * 测试类
 * 注意覆盖率统计的范围
 * 除却实体还有Enum枚举、普通异常等相关的代码需要进行覆盖
 */
class MainAppTest {

    /**
     * 可覆盖Lombok注解构建的实体，但仅限与无参构造函数和getter、setter的调用（其他调用覆盖需额外测试或者补充）
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
     * 测试工具类兼容性不足，仅限于普通实体的getter、setter覆盖
     * 存在不足（Lombok构建的实体无法覆盖）
     */
    @SneakyThrows
    @Test
    void testEntityVoTestUtil(){
        List<Class<?>> classes = ClassUtil.getClasses("com.noob.base.model.entity");
        EntityVoTestUtil.justRun(classes);
    }
}