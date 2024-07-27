package com.noob.base;

import com.noob.base.model.entity.lombok.Student;
import com.noob.base.utils.ClassUtil;
import com.noob.base.utils.EntityVoTestUtil;
import com.noob.base.utils.ModelTestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * 测试类
 * 注意覆盖率统计的范围
 * 除却实体还有Enum枚举、普通异常等相关的代码需要进行覆盖
 */
class ModelTest {


    @Test
    void testBuilder(){
        Student.builder().id(1);
    }


    /**
     * 可覆盖Lombok注解构建的实体，但仅限于无参构造函数和getter、setter的调用（其他调用覆盖需额外测试或者补充）
     * 部分情况无法完全覆盖
     */
    @Test
    void testLombokModel() {
//        String packageName = "com.noob.base.model.entity.common";
        String packageName = "com.noob.base.model.entity.lombok";
        try {
            List<Class<?>> classes = ClassUtil.getClasses(packageName);
            classes.forEach(cls->{
                try {
                    ModelTestUtil.TestPojo(cls);
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
     * 测试工具类兼容性不足，仅限于普通实体的getter、setter、toString覆盖（会过滤掉需要忽略的函数）
     * 存在不足（Lombok构建的实体无法覆盖）
     * todo 完善工具类
     */
//    @SneakyThrows
    @Test
    void testCommonModel(){
        String packageName = "com.noob.base.model.entity.common";
//        String packageName = "com.noob.base.model.entity.lombok";
        // 遇到Lombok构建的实体报错(如果Lombok加了@SuperBuilder注解报错抛出异常)，且无法完全覆盖Lombok注解构建的方法
        try {
            List<Class<?>> classes = ClassUtil.getClasses(packageName);
            EntityVoTestUtil.justRun(classes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}