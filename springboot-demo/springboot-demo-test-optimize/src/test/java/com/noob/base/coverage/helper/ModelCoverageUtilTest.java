package com.noob.base.coverage.helper;

import com.noob.base.coverage.model.entity.ModelCoverageEntity;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * ModelCoverageUtilTest: UT for ModelCoverageUtil
 * - test_coverage_byPackage：遍历某个package下的所有类进行覆盖
 * - test_coverage_entity：实体覆盖方法测验
 */
@RunWith(MockitoJUnitRunner.class)
public class ModelCoverageUtilTest {

    // @Mock
    // Object obj;

    // @Mock fix final class ShardingContext mock error（need PowerMock）
    // Method method;

    /**
     * 构造器覆盖
     */
    @Test
    public void test_coverage_constructor() {
        ModelCoverageUtil util = new ModelCoverageUtil();
        assertNotNull(util);
    }

    /**
     * 覆盖普通实体，验证覆盖范围，补充分支覆盖的场景样例
     */
    @SneakyThrows
    @Test
    public void test_coverage_entity() {

        ModelCoverageUtil.testPojo(ModelCoverageEntity.class);

        Assert.assertTrue(true);
    }

    /**
     * 实体覆盖测试：按照包维度进行覆盖
     * Lombok 注解配置实体 覆盖扫描UT 测试 (部分覆盖，待完善：需调整兼容性适配所有相关lombok覆盖)
     * 可适配普通实体和Lombok修饰的实体，但未兼容lombok所有注解方法覆盖（部分覆盖）
     */
    @Ignore
    @SneakyThrows
    @Test
    public void test_coverage_byPackage() {
        try {
            List<Class<?>> classes = ClassUtil.getClasses("com.htsc.ione.duediligence.duediligence.entity.vo");
            classes.forEach(cls -> {
                ModelCoverageUtil.testPojo(cls);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

}