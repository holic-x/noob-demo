package com.noob.base.coverage.helper;

import com.noob.base.coverage.mockEntity.ExceptionInGetterData;
import com.noob.base.coverage.mockEntity.model.entity.ModelCoverageEntity;
import com.noob.base.coverage.utils.EnhanceModelCoverageUtil;
import com.noob.base.coverage.utils.EqualsHashCodeFullCoverageUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * coverage mock entity （针对测试工具类涉及到的Mock Entity实体进行全覆盖）
 * - EnhanceModelCoverageUtil（实体覆盖增强工具类）
 * - EqualsHashCodeFullCoverageUtil（equals & hashCode 覆盖增强工具类
 * - mock Entity 补充一些特殊场景的实体定义
 */
public class MockEntityCoverageTest {

    // 获取所有字段名称
    private static List<String> getAllFieldNames(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    // coverage AllTypesData
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_AllTypesData() {
        // base coverage
        EnhanceModelCoverageUtil.testEntity(AllTypesData.class);

        // enhance coverage (equals、hashCode)
        new EqualsHashCodeFullCoverageUtil<>(
                AllTypesData::new,
                getAllFieldNames(AllTypesData.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();

    }

    // coverage BuilderEntity
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_BuilderEntity() {
        // base coverage
        EnhanceModelCoverageUtil.testEntity(BuilderEntity.class);

        // enhance coverage (equals、hashCode)
        Supplier<BuilderEntity> supplier = () -> BuilderEntity.builder()
                .id(1L)
                .name("Test")
                .price(10.0)
                .stock(5)
                .build();
        new EqualsHashCodeFullCoverageUtil<>(
                supplier,
                getAllFieldNames(BuilderEntity.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();
    }

    // coverage DeLombokUser
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_DeLombokUser() {
        // base coverage
        EnhanceModelCoverageUtil.testEntity(DeLombokUser.class);

        // enhance coverage (equals、hashCode)
        new EqualsHashCodeFullCoverageUtil<>(
                DeLombokUser::new,
                getAllFieldNames(DeLombokUser.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();
    }

    // coverage EmptyData
    @Test
    public void test_coverage_mock_entity_EmptyData() {
        EmptyData obj1 = new EmptyData();
        EmptyData obj2 = new EmptyData();
        EmptyData obj3 = new EmptyData();
        Object diffObj = new Object();

        obj1.equals(obj1); // 自反性
        obj1.equals(null); // 非空性

        // 对称性
        obj1.equals(obj2);
        obj2.equals(obj1);

        // 传递性
        obj1.equals(obj2);
        obj2.equals(obj3);
        obj3.equals(obj1);

        // 一致性（多次调用结果一致）
        obj1.equals(obj2);
        obj1.equals(obj2);
        obj1.equals(obj2);

        // 与不同类型的对象比较
        // obj1.canEqual(diffObj);

        obj1.hashCode();
        obj1.toString();

        // 补充分支覆盖：子类用于测试 canEqual 分支（覆盖canEqual返回false场景进行测试）
        class NonEqualEmptyData extends EmptyData {
            @Override
            protected boolean canEqual(Object other) {
                return false; // 这将使 canEqual 检查失败
            }
        }
        // cover 覆盖 canEqual 返回 false 使检查失败的场景
        new NonEqualEmptyData().equals(new NonEqualEmptyData());

        // 断言
        Assert.assertNotNull(obj1);

    }

    // coverage ExceptionInGetterData
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_ExceptionInGetterData() {
        ExceptionInGetterData entity = new ExceptionInGetterData();
        Assert.assertThrows(IllegalStateException.class, () -> {
            entity.getName();
        });
        assertTrue(entity.equals(new ExceptionInGetterData()));
        Assert.assertEquals(1, entity.hashCode());
        Assert.assertEquals("ExceptionInGetterData", entity.toString());
    }


    // coverage ModelCoverageEntity
    @Test
    public void test_coverage_mock_entity_ModelCoverageEntity() {
        // base coverage
        EnhanceModelCoverageUtil.testEntity(ModelCoverageEntity.class);

        // other coverage
        ModelCoverageEntity entity = new ModelCoverageEntity();
        entity.setParam();
        entity.randomParam();
    }

    // coverage NonLombokEntity
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_NonLombokEntity() {
        // base coverage
        EnhanceModelCoverageUtil.testEntity(NonLombokEntity.class);

        // other coverage
        NonLombokEntity entity = new NonLombokEntity();
        entity.equals(entity);

        // enhance coverage (equals、hashCode)
        new EqualsHashCodeFullCoverageUtil<>(
                NonLombokEntity::new,
                getAllFieldNames(NonLombokEntity.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();
    }

    // coverage PrivateFieldEntity
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_PrivateFieldEntity() {
        // base coverage
        EnhanceModelCoverageUtil.testEntity(PrivateFieldEntity.class);

        // other coverage
        PrivateFieldEntity entity = new PrivateFieldEntity();
        Assert.assertEquals("initial", entity.revealSecret());
        entity.changeSecret("new Value");
    }


// =========================================================================================
// other coverage test
// =========================================================================================

    @Ignore
    @Test
    public void test_coverage_ContainFinalFieldData() {
        // DataAnnotationCoverageUtil.testDataClass(ContainFinalFieldData.class); // lombok 注解覆盖
        // EnhanceModelCoverageUtil.testEntity(ContainFinalFieldData.class); // 仅覆盖了部分@Data注解生成的方法，分支覆盖有待提升
        assertTrue(Boolean.TRUE);
    }

    @SneakyThrows
    @Test
    public void test_coverage_LombokUser_with_enhance() {
        // line 100%,branch 88%
        EnhanceModelCoverageUtil.testEntity(DeLombokUser.class);

        // 配置测试器
        /*
        EqualsHashCodeFullCoverageUtil<DeLombokUser> tester = new EqualsHashCodeFullCoverageUtil<>(
                DeLombokUser::new,
                Arrays.asList("id", "username"),  // 指定显著字段
                Arrays.asList("password")        // 指定排除字段
        );
         */

        // 针对Equals、hashCode 进行增强覆盖，补足分支覆盖
        EqualsHashCodeFullCoverageUtil<DeLombokUser> tester = new EqualsHashCodeFullCoverageUtil<>(
                DeLombokUser::new,
                Arrays.asList("id", "username", "email"),  // 指定显著字段
                new ArrayList<>()        // 指定排除字段
        );

        // 执行全面测试
        tester.test();
    }

}

// =================================== 辅助测试实体 ===================================

// =========================================================================================================
// - Test case entity 故意破坏hashCode方法
// =========================================================================================================
class BrokenEqualsReturnFalseData {

    private String field;

    @Override
    public boolean equals(Object obj) {
        return false; // 总是返回false
    }
}

class BrokenHashCodeReturnSameData {

    private String field;

    @Override
    public int hashCode() {
        return 1; // 总是返回相同的hashCode
    }
}

@Data
@EqualsAndHashCode
class BrokenHashCodeReturnRandomData {

    private String name;

    private Random rand = new Random(); // 类级别变量
    // this.rand.nextInt();

    @Override
    public int hashCode() {
        // fix Save and re-use this "Random".
        // return new Random().nextInt(); // Not consistent
        return rand.nextInt();// fix code smell

        // return SecureRandom.getInstanceStrong().nextInt(); // 更安全的实现
    }
}

// =========================================================================================================
// - @SuperBuilder 继承结构概念
// =========================================================================================================
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
class SuperBuilderEntity {
    protected String name;
    protected int age;
}

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
class SubSuperBuilderEntity extends SuperBuilderEntity {
    private String department;
    private double salary;
}

// =========================================================================================================
// -  Lombok @Data 注解修饰实体
// =========================================================================================================
@Data
class LombokEntity {
    private Long id;
    private String username;
    private String email;
    // private boolean active;
}

// =========================================================================================================
// -  普通实体类（无Lombok）
// =========================================================================================================
class NonLombokEntity {
    private Long id;
    private String name;
    private int age;

    // 无参构造器
    public NonLombokEntity() {
    }

    // 全参构造器
    public NonLombokEntity(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // getter/setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // equals/hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonLombokEntity nonLombokEntity = (NonLombokEntity) o;
        return age == nonLombokEntity.age &&
                Objects.equals(id, nonLombokEntity.id) &&
                Objects.equals(name, nonLombokEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    // toString
    @Override
    public String toString() {
        return "NonLombokEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


// =========================================================================================================
// -  Lombok Builder 注解修饰实体
// =========================================================================================================
@Data
@Builder
// @NoArgsConstructor // 补充无参构造器
// @AllArgsConstructor // 补充全参构造器
class BuilderEntity {
    private Long id;
    private String name;
    private double price;
    private int stock;
}

// =========================================================================================================
// -  测试空属性的实体（不包括任何属性）
// =========================================================================================================
@Data
class EmptyData {
    // 没有任何属性定义
}

// =========================================================================================================
// -  私有字段属性测试
// =========================================================================================================
class PrivateFieldEntity {
    private String secret = "initial";
    private final int immutable = 42;

    // 没有标准getter/setter
    public String revealSecret() {
        return secret;
    }

    public void changeSecret(String newValue) {
        secret = newValue;
    }
}


// =========================================================================================================
// -  构造函数私有化实体
// =========================================================================================================
class PrivateConstructorEntity {
    private PrivateConstructorEntity() {
    }
}

// =========================================================================================================
// -  没有无参构造器的实体
// =========================================================================================================

class NoExistNoArgsConstructorEntity {
    private String name;

    // 只有带参构造器
    public NoExistNoArgsConstructorEntity(String name) {
        this.name = name;
    }

    // 如果没有显式定义这个，就没有无参构造器
    // public NoExistNoConstructorEntity() {}
}


// =========================================================================================================
// -  没有全参构造器的实体
// =========================================================================================================
class NoExistAllArgsConstructorEntity {
    private String name;
    private String dept;

    // 无参构造器
    public NoExistAllArgsConstructorEntity() {
    }

    /*
    public NoExistAllArgsConstructorEntity(String name) {
        this.name = name;
    }
     */

}
