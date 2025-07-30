package com.noob.base.batchDataHandler.docHandle.otherTest;

import com.noob.base.batchDataHandler.docHandle.v1.MockFileService;
import org.junit.*;
import java.io.*;
import java.lang.reflect.Method;

public class MockFileServiceTest {

    private File tempDir;

    @Before
    public void setUp() {
        tempDir = new File(System.getProperty("java.io.tmpdir"), "mockfileservice_test_" + System.nanoTime());
        Assert.assertTrue(tempDir.mkdirs());
    }

    @After
    public void tearDown() {
        for (File f : tempDir.listFiles()) {
            f.delete();
        }
        tempDir.delete();
    }

    private void createTempFile(String name) throws IOException {
        File file = new File(tempDir, name);
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(new byte[]{1, 2, 3});
        }
    }

    @Test
    public void testGetFileStreamByFileKey_Normal() throws Exception {
        createTempFile("imgkey.png");
        MockFileService service = new MockFileService(tempDir.getAbsolutePath());
        InputStream in = service.getFileStreamByFileKey("imgkey");
        Assert.assertNotNull(in);
        Assert.assertEquals(1, in.read());
        in.close();
    }

    @Test
    public void testGetFileStreamByFileName_Normal() throws Exception {
        createTempFile("testfile.png");
        MockFileService service = new MockFileService(tempDir.getAbsolutePath());
        InputStream in = service.getFileStreamByFileName("testfile.png");
        Assert.assertNotNull(in);
        Assert.assertEquals(1, in.read());
        in.close();
    }

    @Test(expected = FileNotFoundException.class)
    public void testGetFileStreamByFileKey_NotFound() throws Exception {
        MockFileService service = new MockFileService(tempDir.getAbsolutePath());
        service.getFileStreamByFileKey("not_exist");
    }

    @Test(expected = FileNotFoundException.class)
    public void testGetFileStreamByFileName_NotFound() throws Exception {
        MockFileService service = new MockFileService(tempDir.getAbsolutePath());
        service.getFileStreamByFileName("not_exist.png");
    }

    @Test
    public void testMockHandleTime_Branches() throws Exception {
        MockFileService service = new MockFileService(tempDir.getAbsolutePath());
        Method method = MockFileService.class.getDeclaredMethod("mockHandleTime", boolean.class);
        method.setAccessible(true);

        // isRandom = true
        method.invoke(service, true);

        // isRandom = false
        method.invoke(service, false);
    }

    @Test
    public void testConstructor() {
        MockFileService service = new MockFileService(tempDir.getAbsolutePath());
        Assert.assertNotNull(service);
    }
}