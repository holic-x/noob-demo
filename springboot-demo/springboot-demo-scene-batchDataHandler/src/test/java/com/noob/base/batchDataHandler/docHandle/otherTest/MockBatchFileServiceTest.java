package com.noob.base.batchDataHandler.docHandle.otherTest;

import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import com.noob.base.batchDataHandler.docHandle.v2_optimize_batchGet.MockBatchFileService;
import org.junit.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

public class MockBatchFileServiceTest {

    private File tempDir;

    @Before
    public void setUp() {
        tempDir = new File(System.getProperty("java.io.tmpdir"), "mockbatchfile_test_" + System.nanoTime());
        Assert.assertTrue(tempDir.mkdirs());
    }

    @After
    public void tearDown() {
        for (File f : tempDir.listFiles()) {
            f.delete();
        }
        tempDir.delete();
    }

    private FileInfo createFileInfo(String fileName, String fileKey) throws IOException {
        File file = new File(tempDir, fileName);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("testdata");
        }
        FileInfo info = new FileInfo();
        info.setFileName(fileName);
        info.setFileKey(fileKey);
        return info;
    }

    @Test
    public void testGetFileStreamByFileInfo_Normal() throws Exception {
        MockBatchFileService service = new MockBatchFileService(tempDir.getAbsolutePath());
        FileInfo info = createFileInfo("a.txt", "k1");
        InputStream in = service.getFileStreamByFileInfo(info);
        Assert.assertNotNull(in);
        in.close();
    }

    @Test(expected = FileNotFoundException.class)
    public void testGetFileStreamByFileInfo_FileNotFound() throws Exception {
        MockBatchFileService service = new MockBatchFileService(tempDir.getAbsolutePath());
        FileInfo info = new FileInfo();
        info.setFileName("not_exist.txt");
        info.setFileKey("k2");
        service.getFileStreamByFileInfo(info);
    }

    @Test
    public void testGetFileStreamByFileInfo_forBatch_Normal() throws Exception {
        MockBatchFileService service = new MockBatchFileService(tempDir.getAbsolutePath());
        FileInfo info1 = createFileInfo("b.txt", "k3");
        FileInfo info2 = createFileInfo("c.txt", "k4");
        List<FileInfo> list = Arrays.asList(info1, info2);
        Map<String, InputStream> result = service.getFileStreamByFileInfo_forBatch(list);
        Assert.assertEquals(2, result.size());
        for (InputStream in : result.values()) {
            Assert.assertNotNull(in);
            in.close();
        }
    }

    @Test
    public void testGetFileStreamByFileInfo_forBatch_Empty() throws Exception {
        MockBatchFileService service = new MockBatchFileService(tempDir.getAbsolutePath());
        List<FileInfo> list = new ArrayList<>();
        Map<String, InputStream> result = service.getFileStreamByFileInfo_forBatch(list);
        Assert.assertTrue(result.isEmpty());
    }

    @Test(expected = FileNotFoundException.class)
    public void testGetFileStreamByFileInfo_forBatch_FileNotFound() throws Exception {
        MockBatchFileService service = new MockBatchFileService(tempDir.getAbsolutePath());
        FileInfo info1 = createFileInfo("d.txt", "k5");
        FileInfo info2 = new FileInfo();
        info2.setFileName("not_exist2.txt");
        info2.setFileKey("k6");
        List<FileInfo> list = Arrays.asList(info1, info2);
        // Should throw FileNotFoundException
        service.getFileStreamByFileInfo_forBatch(list);
    }

    @Test
    public void testMockHandleTime_Branches() throws Exception {
        MockBatchFileService service = new MockBatchFileService(tempDir.getAbsolutePath());
        Method method = MockBatchFileService.class.getDeclaredMethod("mockHandleTime", List.class);

        method.setAccessible(true);

        // size = 0
        method.invoke(service, Collections.emptyList());

        // size = 1 (<=500)
        FileInfo info = createFileInfo("e.txt", "k7");
        method.invoke(service, Arrays.asList(info));

        // size = 501 (>500 && <=1000)
        List<FileInfo> list501 = new ArrayList<>();
        for (int i = 0; i < 501; i++) {
            FileInfo fi = new FileInfo();
            fi.setFileName("f" + i + ".txt");
            fi.setFileKey("k" + i);
            list501.add(fi);
        }
        method.invoke(service, list501);

        // size = 1001 (>1000 && <=2000)
        List<FileInfo> list1001 = new ArrayList<>();
        for (int i = 0; i < 1001; i++) {
            FileInfo fi = new FileInfo();
            fi.setFileName("g" + i + ".txt");
            fi.setFileKey("k" + i);
            list1001.add(fi);
        }
        method.invoke(service, list1001);

        // size > 2000 (should throw)
        List<FileInfo> list2001 = new ArrayList<>();
        for (int i = 0; i < 2001; i++) {
            FileInfo fi = new FileInfo();
            fi.setFileName("h" + i + ".txt");
            fi.setFileKey("k" + i);
            list2001.add(fi);
        }
        try {
            method.invoke(service, list2001);
            Assert.fail("Should throw RuntimeException");
        } catch (Exception e) {
            Assert.assertTrue(e.getCause() instanceof RuntimeException);
        }
    }
}