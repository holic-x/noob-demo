package com.noob.base.coverage.helper;

import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

/**
 * UT for EqualsHashCodeFullCoverageUtil
 */
public class EqualsHashCodeFullCoverageUtilTest {

    @Test
    public void test_coverage_constructor() {
        // 构造器
        EqualsHashCodeFullCoverageUtil<AllTypesData> util1 = new EqualsHashCodeFullCoverageUtil<>(
                AllTypesData::new
        );
        assertNotNull(util1);

        // 构造器
        EqualsHashCodeFullCoverageUtil<AllTypesData> util2 = new EqualsHashCodeFullCoverageUtil<>(
                AllTypesData::new,
                Arrays.asList("pBoolean"), // 需覆盖字段
                Arrays.asList("pByte") // 忽略字段
        );
        assertNotNull(util2);
    }


    // coverage AllTypesData 不指定任务字段
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_AllTypesData_nothing() {

        // enhance coverage (equals、hashCode)
        new EqualsHashCodeFullCoverageUtil<>(
                AllTypesData::new
        ).test();

    }

    // coverage AllTypesData
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_AllTypesData() {
        new EqualsHashCodeFullCoverageUtil<>(
                AllTypesData::new,
                InvokeHelperUtil.getAllFieldNames(AllTypesData.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();

    }

    // coverage OtherTypesData
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_OtherTypesData() {
        new EqualsHashCodeFullCoverageUtil<>(
                OtherTypesData::new,
                InvokeHelperUtil.getAllFieldNames(OtherTypesData.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();

    }

    // coverage EnumData
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_EnumData() {
        new EqualsHashCodeFullCoverageUtil<>(
                EnumData::new,
                InvokeHelperUtil.getAllFieldNames(EnumData.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();

    }

    // coverage NoConstantEnumData
    @SneakyThrows
    @Test
    public void testNoConstantEnumData() {
        new EqualsHashCodeFullCoverageUtil<>(
                NoConstantEnumData::new,
                InvokeHelperUtil.getAllFieldNames(NoConstantEnumData.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();
    }

    // ================= 特殊类定义补充覆盖测试 ==========================
    // coverage SpecialFieldData
    @SneakyThrows
    @Test
    public void test_coverage_mock_entity_SpecialFieldData() {
        // base coverage
        EnhanceEntityCoverageUtil.testEntity(SpecialFieldData.class);

        // enhance coverage (equals、hashCode)
        /*new EqualsHashCodeFullCoverageUtil<>(
                SpecialFieldData::new,
                getAllFieldNames(SpecialFieldData.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();
         */

        // other coverage
        SpecialFieldData obj1 = new SpecialFieldData();
        SpecialFieldData obj2 = new SpecialFieldData();
        SpecialFieldData obj3 = new SpecialFieldData();
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
        obj1.canEqual(diffObj);

        obj1.hashCode();
        obj1.toString();

        // 补充分支覆盖：子类用于测试 canEqual 分支（覆盖canEqual返回false场景进行测试）
        class SubTest extends SpecialFieldData {
            @Override
            protected boolean canEqual(Object other) {
                return false; // 这将使 canEqual 检查失败
            }
        }
        // cover 覆盖 canEqual 返回 false 使检查失败的场景
        new SubTest().equals(new SubTest());


        // 覆盖不同属性的equals\hashCode
        SpecialFieldData data1 = new SpecialFieldData();
        SpecialFieldData data2 = new SpecialFieldData();
        SpecialFieldData data3 = new SpecialFieldData();
        SpecialFieldData data4 = new SpecialFieldData();


        data1.setName("data1"); // this(data1)
        data1.hashCode();
        data2.setName(null);
        data1.equals(data2); // that.name == null
        data3.setName("data3");
        data1.equals(data3); // that.name != null && this.name != that.name
        data4.setName("data1");
        data1.equals(data4); // that.name != null && this.name == that.name

        obj1.equals(data1); // this(obj1) name==null

        // 断言
        Assert.assertNotNull(obj1);

    }

    @SneakyThrows
    @Test
    public void test_BrokenHashCodeReturnSameData() {
        new EqualsHashCodeFullCoverageUtil<>(
                BrokenHashCodeReturnSameData::new,
                InvokeHelperUtil.getAllFieldNames(BrokenHashCodeReturnSameData.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();
    }

    @SneakyThrows
    @Test
    public void test_BrokenHashCodeReturnRandomData() {
        new EqualsHashCodeFullCoverageUtil<>(
                BrokenHashCodeReturnRandomData::new,
                InvokeHelperUtil.getAllFieldNames(BrokenHashCodeReturnRandomData.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();
    }

    @SneakyThrows
    @Test
    public void test_SuperBuilderEntity() {
        new EqualsHashCodeFullCoverageUtil<>(
                SuperBuilderEntity::new,
                InvokeHelperUtil.getAllFieldNames(SuperBuilderEntity.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();
    }

    @SneakyThrows
    @Test
    public void test_SubSuperBuilderEntity() {
        new EqualsHashCodeFullCoverageUtil<>(
                SubSuperBuilderEntity::new,
                InvokeHelperUtil.getAllFieldNames(SubSuperBuilderEntity.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        ).test();
    }

    @SneakyThrows
    // @Test(expected = IllegalArgumentException.class)
    @Test
    public void test_PrivateDataWrapper() {
        EqualsHashCodeFullCoverageUtil util = new EqualsHashCodeFullCoverageUtil<>(
                PrivateDataWrapper::new,
                InvokeHelperUtil.getAllFieldNames(PrivateDataWrapper.class), // 覆盖兼容字段
                new ArrayList<>()        // 指定排除字段
        );

        assertThrows(IllegalArgumentException.class, () -> {
            util.test();
        });
    }

}


// ============================================== mock entity ==================================
// 其他类型验证
@Data
class OtherTypesData {
    private List<String> strList; // List 类型
    private ArrayList<String> strArrayList; // List 类型：补充覆盖List子类集合场景
    // private LinkedList<String> strLinkedList; // List 类型

    private Set<String> strSet; // Set 类型
    private Map<String, Integer> map; // Map 类型
    private String[] strArr;// 数组类型

    // private String trueStr = "true"; // "true" 字符串
    // private String falseStr = "false"; // "false" 字符串

    private Character nullCharacter = null;
    private char numChar = '1'; // 数字类型字符
    private char letterChar = 'A'; // 字母类型字符

    // 兼容不同的日期类型处理
    private java.util.Date date1; // 虽然getModifiedValue没有显示对这个字段进行处理，但是将其当做实体类型处理，调用了其构造器实现初始化构造
    private java.sql.Date date2; // 同理虽然getModifiedValue没有显示对这个字段进行处理，但是无法转化，原因在于没有合适的构造器处理,如果要完全适配兼容则需类似初始化概念一样手动构建即可
    private java.util.Calendar date3;
    private java.time.LocalDate date4;
    private java.time.LocalDateTime date5;
    private java.time.LocalTime date6;

    // 其他常用的Java类型
    private BigDecimal bigDecimalData;
    private UUID randomUUID;

}


// 定义一个实体类：其有B b属性，将B类构造函数私有化，以此模拟字段设置异常的问题
@Data
class PrivateDataWrapper {
    private PrivateData privateData;
}

class PrivateData {
    // 构造函数私有化
    private PrivateData() {

    }
}


