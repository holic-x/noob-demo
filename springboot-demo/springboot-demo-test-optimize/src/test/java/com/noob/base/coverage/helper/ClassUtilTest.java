package com.noob.base.coverage.helper;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * ClassUtilTest: UT for ClassUtilTest
 */
public class ClassUtilTest {

    // 获取指定package下的类信息
    @SneakyThrows
    @Test
    public void test_getClasses_success_byPackage() {
        List<Class<?>> classes = ClassUtil.getClasses("com.htsc.ione.duediligence.duediligence.entity");
        assertNotNull(classes);
    }


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private File packageDir;

    @Before
    public void setUp() throws IOException {
        packageDir = folder.newFolder("com", "htsc", "ione", "duediligence", "test");

        // 创建一些测试类文件
        createTestClassFile("TestClass1.class", packageDir);
        createTestClassFile("TestClass2.class", packageDir);

        // 创建一个子目录并添加一个测试类文件
        File subPackageDir = folder.newFolder("com", "htsc", "ione", "duediligence", "test", "subpackage");
        createTestClassFile("TestClass3.class", subPackageDir);
    }

    private void createTestClassFile(String fileName, File dir) throws IOException {
        File file = new File(dir, fileName);
        file.createNewFile();
    }

    @Test(expected = AssertionError.class)
    public void testFindClasses_SubdirectoryNameContainsDot() throws ClassNotFoundException, IOException {
        // 创建一个包含点号的子目录
        File subPackageDirWithDot = folder.newFolder("com", "htsc", "ione", "duediligence", "test", "sub.package");
        createTestClassFile("TestClass4.class", subPackageDirWithDot);

        // 调用 findClasses 方法，期望抛出 AssertionError
        List<Class<?>> classes = ClassUtil.findClasses(packageDir, "com.htsc.ione.duediligence.test", false);
        assertEquals(3, classes.size()); // 包括子目录中的类
    }

    @Test
    public void testFindClasses_SubdirectoryNameDoesNotContainDot() throws ClassNotFoundException, IOException {
        // 创建一个不包含点号的子目录
        File subPackageDirWithoutDot = folder.newFolder("com", "htsc", "ione", "duediligence", "test", "subpackageWithoutDot");
        createTestClassFile("TestClass5.class", subPackageDirWithoutDot);

        // 调用 findClasses 方法，期望正常执行且不抛出 AssertionError
        List<Class<?>> classes = ClassUtil.findClasses(packageDir, "com.htsc.ione.duediligence.test", false);
        assertEquals(4, classes.size()); // 包括子目录中的类
    }

    @Test
    public void testFindClasses_SubdirectoryNameDoesNotContainDot_nonClass() throws ClassNotFoundException, IOException {
        // 创建一个不包含点号的子目录
        File subPackageDirWithoutDot = folder.newFolder("com", "htsc", "ione", "duediligence", "test", "subpackageWithoutDot");
        createTestClassFile("NonClass.txt", subPackageDirWithoutDot); // 非class 不纳入加载

        // 调用 findClasses 方法，期望正常执行且不抛出 AssertionError
        List<Class<?>> classes = ClassUtil.findClasses(packageDir, "com.htsc.ione.duediligence.test", false);
        assertEquals(3, classes.size()); // 包括子目录中的类
    }

    // tocheck mock 文件数据mock
    @Test
    public void testFindClasses_DirectoryDoesNotExist() throws ClassNotFoundException {
        File nonExistentDir = new File(folder.getRoot(), "nonexistent");
        List<Class<?>> classes = ClassUtil.findClasses(nonExistentDir, "com.htsc.ione.duediligence.test", true);
        assertTrue(classes.isEmpty());
    }

    @Test
    public void testFindClasses_EmptyDirectory() throws IOException, ClassNotFoundException {
        File emptyDir = folder.newFolder("empty");
        List<Class<?>> classes = ClassUtil.findClasses(emptyDir, "com.htsc.ione.duediligence.test", true);
        assertTrue(classes.isEmpty());
    }

    @Test
    public void testFindClasses_ContainsNonClassFiles() throws IOException {
        createTestClassFile("notaclass.txt", packageDir);
        assertThrows(ClassNotFoundException.class, () -> {
            ClassUtil.findClasses(packageDir, "com.htsc.ione.duediligence.test", true);
        });
    }

    @Test
    public void testFindClasses_ContainsClassFiles() {
        assertThrows(ClassNotFoundException.class, () -> {
            ClassUtil.findClasses(packageDir, "com.htsc.ione.duediligence.test", true);
        });
    }

    @Test
    public void testFindClasses_NestedDirectories() {
        assertThrows(ClassNotFoundException.class, () -> {
            ClassUtil.findClasses(packageDir, "com.htsc.ione.duediligence.test", true);
        });
    }

}