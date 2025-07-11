package com.noob.base.coverage.helper;

import com.noob.base.coverage.mockEntity.validCover.CommonEntity;
import com.noob.base.coverage.mockEntity.validCover.LombokEntityByBuilderAnnotation;
import com.noob.base.coverage.mockEntity.validCover.LombokEntityByDataAnnotation;
import com.noob.base.coverage.mockEntity.validCover.LombokEntityBySuperBuilderAnnotation;
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

        EnhanceModelCoverageUtil.testEntity(CommonEntity.class);

        Assert.assertTrue(true);
    }

    /**
     * 测试场景-Lombok修饰的实体覆盖 @Data 注解修饰
     */
    // @SneakyThrows
    @Test
    public void test_coverage_LombokEntityByDataAnnotation() {

        EnhanceModelCoverageUtil.testEntity(LombokEntityByDataAnnotation.class);

        Assert.assertTrue(true);
    }

    /**
     * 测试场景-Lombok修饰的实体覆盖 @Builder 注解修饰
     * - 此处需注意 @Builder 需与 @NoArgsConstructor、@AllArgsConstructor 搭配使用，否则测试会提示创建实例失败导致覆盖异常
     * - 如果出现实例创建失败的测试场景，则进一步排查目标覆盖实体类是否正确提供了构造器（例如如果提供了私有构造器则可能由于权限问题无法正常访问）
     */
    // @SneakyThrows
    @Test
    public void test_coverage_LombokEntityByBuilderAnnotation() {

        EnhanceModelCoverageUtil.testEntity(LombokEntityByBuilderAnnotation.class);

        Assert.assertTrue(true);
    }


    /**
     * 测试场景-Lombok修饰的实体覆盖 @SuperBuilder 注解修饰
     * 此处覆盖的是子类对象，如果子类继承了覆盖并指定了@SuperBuilder注解，可通过此工具类进行覆盖，但是需注意父类的覆盖可能还需要额外进行覆盖处理
     */
    // @SneakyThrows
    @Test
    public void test_coverage_LombokEntityBySuperBuilderAnnotation() {

        EnhanceModelCoverageUtil.testEntity(LombokEntityBySuperBuilderAnnotation.class);

        Assert.assertTrue(true);
    }


}
