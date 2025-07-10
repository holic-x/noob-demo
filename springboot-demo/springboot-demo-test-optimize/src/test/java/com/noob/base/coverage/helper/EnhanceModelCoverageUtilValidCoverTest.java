package com.noob.base.coverage.helper;

import com.noob.base.coverage.mockEntity.validCover.CommonEntity;
import com.noob.base.coverage.mockEntity.validCover.LombokEntityByBuilderAnnotation;
import com.noob.base.coverage.mockEntity.validCover.LombokEntityByDataAnnotation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

/**
 * EnhanceModelCoverageUtil 验证覆盖 测试用例场景
 */
@RunWith(MockitoJUnitRunner.class)
public class EnhanceModelCoverageUtilValidCoverTest {

    /**
     * 测试场景-普通实体的覆盖
     */
    // @SneakyThrows
    @Test
    public void test_coverage_CommonEntity() {

        EnhanceEntityCoverageUtil.testEntity(CommonEntity.class);

        Assert.assertTrue(true);
    }

    /**
     * 测试场景-Lombok修饰的实体覆盖 @Data 注解修饰
     */
    // @SneakyThrows
    @Test
    public void test_coverage_LombokEntityByDataAnnotation() {

        EnhanceEntityCoverageUtil.testEntity(LombokEntityByDataAnnotation.class);

        Assert.assertTrue(true);
    }

    /**
     * 测试场景-Lombok修饰的实体覆盖 @Builder 注解修饰
     * - 此处需注意 @Builder 需与 @NoArgsConstructor、@AllArgsConstructor 搭配使用，否则测试会提示创建实例失败导致覆盖异常
     */
    // @SneakyThrows
    @Test
    public void test_coverage_LombokEntityByBuilderAnnotation() {

        EnhanceEntityCoverageUtil.testEntity(LombokEntityByBuilderAnnotation.class);

        Assert.assertTrue(true);
    }




}
