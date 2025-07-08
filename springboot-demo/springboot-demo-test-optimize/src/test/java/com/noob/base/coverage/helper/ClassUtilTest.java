package com.noob.base.coverage.helper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * todo
 * ClassUtilTest: UT for ClassUtil
 * ClassUtil 工具类的单元测试
 * 测试场景覆盖：
 * 1. 正常类扫描场景
 * 2. 目录结构异常场景
 * 3. 文件类型过滤场景
 * 4. 边界条件测试
 */
@Slf4j
public class ClassUtilTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private File packageDir;

    @Before
    public void setUp() throws IOException {
        // 创建基础测试目录结构
        packageDir = folder.newFolder("com", "noob", "base", "coverage", "test");
    }

    /**
     * 测试工具方法：创建测试类文件
     */
    private void createTestClassFile(String fileName, File dir) throws IOException {
        File file = new File(dir, fileName);
        assertTrue("Failed to create test file: " + file.getAbsolutePath(), file.createNewFile());
    }

    /**
     * 测试场景：正常获取指定包下的类信息（不加载类模式）
     * 覆盖点：基本扫描功能、不加载类模式
     */
    @Test
    public void test_getClasses_withoutLoading() throws Exception {
        // 准备测试数据
        createTestClassFile("TestClass1.class", packageDir);
        createTestClassFile("TestClass2.class", packageDir);

        // 执行测试
        List<Class<?>> classes = ClassUtil.getClasses("com.noob.base.coverage.test", false);

        // 验证结果
        assertNotNull(classes);
        assertEquals(2, classes.size());
        assertNull(classes.get(0)); // 验证未实际加载类
    }

    /**
     * 测试场景：正常获取指定包下的类信息（加载类模式）
     * 覆盖点：类加载功能、真实类加载
     */
    @Test
    public void test_getClasses_withLoading() throws Exception {
        // 使用当前测试类所在包作为测试数据
        String currentPackage = this.getClass().getPackage().getName();

        // 执行测试
        List<Class<?>> classes = ClassUtil.getClasses(currentPackage, true);

        // 验证结果
        assertFalse(classes.isEmpty());
        assertNotNull(classes.get(0)); // 验证类已加载
        log.info("加载的类: {}", classes.get(0).getName());
    }

    /**
     * 测试场景：扫描包含子包的目录结构
     * 覆盖点：递归扫描功能、子包处理
     */
    @Test
    public void test_findClasses_withSubPackages() throws Exception {
        // 准备测试数据
        createTestClassFile("TestClass1.class", packageDir);
        File subPackageDir = folder.newFolder("com", "noob", "base", "coverage", "test", "subpackage");
        createTestClassFile("SubClass1.class", subPackageDir);

        // 执行测试
        List<Class<?>> classes = ClassUtil.findClasses(packageDir, "com.noob.base.coverage.test", false);

        // 验证结果
        assertEquals(2, classes.size());
    }

    /**
     * 测试场景：目录不存在的情况
     * 覆盖点：容错处理、不存在的目录
     */
    @Test
    public void test_findClasses_directoryNotExist() throws Exception {
        // 准备测试数据
        File nonExistentDir = new File(folder.getRoot(), "nonexistent");

        // 执行测试
        List<Class<?>> classes = ClassUtil.findClasses(nonExistentDir, "com.noob.base.coverage.test", false);

        // 验证结果
        assertTrue(classes.isEmpty());
    }

    /**
     * 测试场景：空目录情况
     * 覆盖点：空目录处理
     */
    @Test
    public void test_findClasses_emptyDirectory() throws Exception {
        // 准备测试数据
        File emptyDir = folder.newFolder("empty");

        // 执行测试
        List<Class<?>> classes = ClassUtil.findClasses(emptyDir, "com.noob.base.coverage.test", false);

        // 验证结果
        assertTrue(classes.isEmpty());
    }

    /**
     * 测试场景：非法包名（包含点号）
     * 覆盖点：非法包名检测、assert语句
     */
    @Test(expected = AssertionError.class)
    public void test_findClasses_invalidPackageNameWithDot() throws Exception {
        // 准备测试数据（创建包含点号的目录）
        File invalidPackageDir = folder.newFolder("com", "noob", "base", "coverage", "invalid.package");
        createTestClassFile("InvalidClass.class", invalidPackageDir);

        // 执行测试（预期抛出AssertionError）
        ClassUtil.findClasses(invalidPackageDir, "com.noob.base.coverage.invalid.package", false);
    }

    /**
     * 测试场景：混合文件类型目录
     * 覆盖点：文件类型过滤、非.class文件处理
     */
    @Test
    public void test_findClasses_mixedFileTypes() throws Exception {
        // 准备测试数据
        createTestClassFile("ValidClass.class", packageDir);
        createTestClassFile("TextFile.txt", packageDir);

        // 执行测试
        List<Class<?>> classes = ClassUtil.findClasses(packageDir, "com.noob.base.coverage.test", false);

        // 验证结果
        assertEquals(1, classes.size()); // 只应包含.class文件
    }

    /**
     * 测试场景：资源获取异常情况
     * 覆盖点：getResources异常处理
     */
    @Test(expected = IOException.class)
    public void test_getClasses_withInvalidPackageName() throws Exception {
        // 使用非法包名（包含特殊字符）
        ClassUtil.getClasses("invalid/package/name", false);
    }

    /**
     * 测试场景：目录无读取权限
     * 覆盖点：权限异常处理
     */
    @Test
    public void test_findClasses_noReadPermission() throws Exception {
        // 准备测试数据
        File noPermissionDir = folder.newFolder("noPermission");
        assertTrue(noPermissionDir.setReadable(false));

        try {
            List<Class<?>> classes = ClassUtil.findClasses(noPermissionDir, "com.noob.base.coverage.test", false);
            assertTrue(classes.isEmpty());
        } finally {
            // 恢复权限
            noPermissionDir.setReadable(true);
        }
    }

    /**
     * 测试场景：类文件但非有效类
     * 覆盖点：无效类文件处理
     */
    @Test(expected = ClassNotFoundException.class)
    public void test_findClasses_invalidClassFile() throws Exception {
        // 准备测试数据（创建无效的.class文件）
        File invalidClassFile = new File(packageDir, "InvalidClass.class");
        assertTrue(invalidClassFile.createNewFile());

        // 执行测试（加载模式）
        ClassUtil.findClasses(packageDir, "com.noob.base.coverage.test", true);
    }

    /**
     * 测试场景：多级嵌套子包
     * 覆盖点：深度递归扫描
     */
    @Test
    public void test_findClasses_deepNestedPackages() throws Exception {
        // 准备测试数据（创建多级子包）
        File subPackage1 = folder.newFolder("com", "noob", "base", "coverage", "test", "sub1");
        File subPackage2 = new File(subPackage1, "sub2");
        assertTrue(subPackage2.mkdir());

        createTestClassFile("Class1.class", packageDir);
        createTestClassFile("Class2.class", subPackage1);
        createTestClassFile("Class3.class", subPackage2);

        // 执行测试
        List<Class<?>> classes = ClassUtil.findClasses(packageDir, "com.noob.base.coverage.test", false);

        // 验证结果
        assertEquals(3, classes.size());
    }
}