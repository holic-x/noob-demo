package com.noob.base.coverage.utils;

import com.noob.base.coverage.mockEntity.SpecialEntity;
import lombok.SneakyThrows;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * 实体 UT 覆盖 测试
 */
public class EnhanceModelCoverageUtilTest {

    @Test
    public void test_coverage_constructor() throws Exception {
        EnhanceModelCoverageUtil enhanceModelCoverageUtil = new EnhanceModelCoverageUtil();
        assertNotNull(enhanceModelCoverageUtil);
    }

    @Test
    public void testSpecialEntity() throws Exception {
        // 测试普通实体类
        EnhanceModelCoverageUtil.testEntity(SpecialEntity.class);
    }


    @Test
    public void test_PrivateConstructorEntity() throws Exception {
        // 测试普通实体类(构造函数私有化：覆盖无参构造器私有化（突破限制）的场景)
        EnhanceModelCoverageUtil.testEntity(PrivateConstructorEntity.class);
    }

    @Test
    public void test_NoExistNoArgsConstructorEntity() throws Exception {
        // 测试普通实体类(无无参构造器的场景)
        EnhanceModelCoverageUtil.testEntity(NoExistNoArgsConstructorEntity.class);
    }

    @Test
    public void test_NoExistAllArgsConstructorEntity() throws Exception {
        // 测试普通实体类(无全参构造器的场景)
        EnhanceModelCoverageUtil.testEntity(NoExistAllArgsConstructorEntity.class);
    }

    @Test
    public void test_NonLombokEntity() throws Exception {
        // 测试普通实体类
        EnhanceModelCoverageUtil.testEntity(NonLombokEntity.class);
    }

    // 测试多种类型的属性（纯@Data）
    @Test
    public void test_LombokEntity() throws Exception {
        // 测试Lombok @Data实体
        EnhanceModelCoverageUtil.testEntity(LombokEntity.class);
    }

    // 测试Builder模式
    @Test
    public void testBuilderEntity() throws Exception {
        // 测试@Builder实体
        EnhanceModelCoverageUtil.testEntity(BuilderEntity.class);
    }

    @Test
    public void testSuperBuilderEntity_exam01() throws Exception {
        // 测试@SuperBuilder继承结构(确保父类和子类都标注了@SuperBuilder)
        EnhanceModelCoverageUtil.testEntity(SubSuperBuilderEntity.class);
    }

//    @Test(expected = AssertionError.class)
//    public void testInvalidSuperBuilder() {
//        // 错误示例：父类没有@SuperBuilder
//        EnhanceModelCoverageUtil.testEntity(ChildData.class); // 应该抛出异常
//    }

    // @Test(expected = AssertionError.class)
    @Test
    public void test_BrokenEqualsData() throws Exception {
        // 测试故意破坏equals方法的实体
        EnhanceModelCoverageUtil.testEntity(BrokenEqualsData.class);

        /*
        // 需补充覆盖 coverage this==obj 分支场景
        BrokenEqualsData obj1 = new BrokenEqualsData();
        BrokenEqualsData obj2 = obj1;
        Assert.assertTrue(obj1.equals(obj2));
         */
    }

    @Test
    public void test_BrokenEqualsReturnFalseData() throws Exception {
        // 测试故意破坏equals方法的实体（总是返回false）
        EnhanceModelCoverageUtil.testEntity(BrokenEqualsReturnFalseData.class);

        /*
        BrokenEqualsReturnFalseData entity = new BrokenEqualsReturnFalseData();
        Assert.assertFalse(entity.equals(new BrokenEqualsReturnFalseData()));
         */
    }

    @SneakyThrows
    @Test
    public void test_BrokenHashCodeReturnSameData() {
        // 测试故意破坏hashCode方法的实体（返回同样的hashCode）
        EnhanceModelCoverageUtil.testEntity(BrokenHashCodeReturnSameData.class);
    }

    // @Test(expected = AssertionError.class)
    @Test
    public void test_BrokenHashCodeReturnRandomData() throws Exception {
        // 测试故意破坏hashCode方法的实体
        EnhanceModelCoverageUtil.testEntity(BrokenHashCodeReturnRandomData.class);
    }

    @Test
    public void test_PrivateFieldEntity() throws Exception {
        // 拥有私有属性的实体
        EnhanceModelCoverageUtil.testEntity(PrivateFieldEntity.class);
    }

    @Test
    public void test_SuperBuilderEntity() throws Exception {
        // 拥有私有属性的实体
        EnhanceModelCoverageUtil.testEntity(SuperBuilderEntity.class);
    }

    @Test
    public void test_SubSuperBuilderEntity() throws Exception {
        // 拥有私有属性的实体
        EnhanceModelCoverageUtil.testEntity(SubSuperBuilderEntity.class);
    }
}
