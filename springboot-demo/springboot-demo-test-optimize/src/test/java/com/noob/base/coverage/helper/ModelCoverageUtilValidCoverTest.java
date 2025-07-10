package com.noob.base.coverage.helper;

import com.noob.base.coverage.mockEntity.forModelCoverageUtil.CommonEntity;
import com.noob.base.coverage.mockEntity.forModelCoverageUtil.LombokEntityByDataAnnotation;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

/**
 * ModelCoverageUtilValidCoverTest: 验证ModelCoverageUtil的覆盖程度(兼容性验证)
 * - 基于不同的实体定义进行覆盖
 */
@RunWith(MockitoJUnitRunner.class)
public class ModelCoverageUtilValidCoverTest {

    /**
     * 测试场景-普通实体的覆盖
     */
    // @SneakyThrows
    @Test
    public void test_coverage_commonEntity() {

        ModelCoverageUtil.testPojo(CommonEntity.class);

        Assert.assertTrue(true);
    }

    /**
     * 测试场景-Lombok修饰的实体覆盖
     */
    // @SneakyThrows
    @Test
    public void test_coverage_LombokEntityByDataAnnotation() {

        ModelCoverageUtil.testPojo(LombokEntityByDataAnnotation.class);

        Assert.assertTrue(true);
    }

    /**
     * 实体覆盖测试：搭配ClassUtil使用，按照扫描package的维度进行覆盖
     * Lombok 注解配置实体 覆盖扫描UT 测试 (部分覆盖，待完善：需调整兼容性适配所有相关lombok覆盖)
     * 可适配普通实体和Lombok修饰的实体，但未兼容lombok所有注解方法覆盖（部分覆盖）
     */
    // @Ignore
    @SneakyThrows
    @Test
    public void test_coverage_byPackage() {
        try {
            List<Class<?>> classes = ClassUtil.getClasses("com.noob.base.coverage.mockEntity.forModelCoverageUtil");
            classes.forEach(cls -> {
                ModelCoverageUtil.testPojo(cls);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

}