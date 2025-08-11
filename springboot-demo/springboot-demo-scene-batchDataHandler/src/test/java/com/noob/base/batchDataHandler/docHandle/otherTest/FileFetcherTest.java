package com.noob.base.batchDataHandler.docHandle.otherTest;

import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import com.noob.base.batchDataHandler.docHandle.v3_optimize_multiThread.FileFetcher;
import org.junit.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;

public class FileFetcherTest {

    private File tempDir;
    private List<FileInfo> fileInfoList;

    @Before
    public void setUp() throws Exception {
        tempDir = new File(System.getProperty("java.io.tmpdir"), "filefetcher_test_" + System.nanoTime());
        Assert.assertTrue(tempDir.mkdirs());
        fileInfoList = new ArrayList<>();
        // 创建3个测试文件
        for (int i = 0; i < 3; i++) {
            String fileName = "test" + i + ".txt";
            File file = new File(tempDir, fileName);
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("content" + i);
            }
            FileInfo info = new FileInfo();
            info.setFileName(fileName);
            info.setFileKey("key" + i);
            fileInfoList.add(info);
        }
    }

    @After
    public void tearDown() throws Exception {
        for (FileInfo info : fileInfoList) {
            File file = new File(tempDir, info.getFileName());
            if (file.exists()) file.delete();
        }
        tempDir.delete();
    }

    @Test
    public void testFetchFiles_Normal() throws Exception {
        FileFetcher fetcher = new FileFetcher(tempDir.getAbsolutePath(), 2);
        Map<String, InputStream> result = fetcher.fetchFiles(fileInfoList);
        Assert.assertEquals(3, result.size());
        for (FileInfo info : fileInfoList) {
            InputStream in = result.get(info.getFileKey());
            Assert.assertNotNull(in);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            Assert.assertTrue(line.startsWith("content"));
            in.close();
        }
    }

    @Test
    public void testFetchFiles_EmptyList() {
        FileFetcher fetcher = new FileFetcher(tempDir.getAbsolutePath(), 2);
        Map<String, InputStream> result = fetcher.fetchFiles(Collections.<FileInfo>emptyList());
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testFetchFiles_FileNotFound() {
        FileInfo badInfo = new FileInfo();
        badInfo.setFileName("not_exist.txt");
        badInfo.setFileKey("badkey");
        List<FileInfo> list = new ArrayList<>(fileInfoList);
        list.add(badInfo);
        FileFetcher fetcher = new FileFetcher(tempDir.getAbsolutePath(), 2);
        Map<String, InputStream> result = fetcher.fetchFiles(list);
        // 只会有3个有效文件流
        Assert.assertEquals(3, result.size());
        Assert.assertNull(result.get("badkey"));
    }

    @Test
    public void testConstructorAndFields() {
        FileFetcher fetcher = new FileFetcher("abc", 1);
        Assert.assertNotNull(fetcher);
    }

    @Test
    public void testGetFileStreamByFileInfo_Private() throws Exception {
        FileFetcher fetcher = new FileFetcher(tempDir.getAbsolutePath(), 1);
        Method method = FileFetcher.class.getDeclaredMethod("getFileStreamByFileInfo", String.class);
        method.setAccessible(true);
        InputStream in = (InputStream) method.invoke(fetcher, fileInfoList.get(0).getFileName());
        Assert.assertNotNull(in);
        in.close();
    }
}