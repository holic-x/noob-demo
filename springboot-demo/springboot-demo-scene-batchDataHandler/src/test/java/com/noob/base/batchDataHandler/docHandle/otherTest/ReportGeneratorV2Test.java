package com.noob.base.batchDataHandler.docHandle.otherTest;

import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import com.noob.base.batchDataHandler.docHandle.entity.KeyInfo;
import com.noob.base.batchDataHandler.docHandle.entity.ResultItem;
import com.noob.base.batchDataHandler.docHandle.v2_optimize_batchGet.ReportGeneratorV2;
import com.noob.base.batchDataHandler.docHandle.v2_optimize_batchGet.MockBatchFileService;
import org.junit.*;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ReportGeneratorV2Test {

    private MockBatchFileService mockFileService;
    private ReportGeneratorV2 generator;
    private File tempFile;

    @Before
    public void setUp() throws Exception {
        mockFileService = mock(MockBatchFileService.class);
        generator = new ReportGeneratorV2(mockFileService);
        tempFile = File.createTempFile("test_report", ".docx");
        tempFile.deleteOnExit();
    }

    @After
    public void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testGenerateReport_Normal() throws Exception {
        // 构造数据
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileKey("img1");
        fileInfo.setFileName("img1.png");

        ResultItem item = new ResultItem();
        item.setKey(new KeyInfo("企业A", "123456", null, null, null, null, null));
        item.setFiles(Collections.singletonList(fileInfo));

        CheckResult checkResult = new CheckResult();
        checkResult.setWebKey("WEB1");
        checkResult.setWebName("网站1");
        checkResult.setStatus("SUCCESS");
        checkResult.setNote("备注信息");
        checkResult.setResults(Collections.singletonList(item));

        List<CheckResult> checkResults = Collections.singletonList(checkResult);

        // Mock图片流
        Map<String, InputStream> fileStreamMap = new HashMap<>();
        fileStreamMap.put("img1", new ByteArrayInputStream(createFakePng()));
        when(mockFileService.getFileStreamByFileInfo_forBatch(anyList())).thenReturn(fileStreamMap);

        generator.generateReport(checkResults, tempFile.getAbsolutePath());

        // 验证文档生成
        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);
    }

    @Test
    public void testGenerateReport_NoFiles() throws Exception {
        ResultItem item = new ResultItem();
        item.setKey(new KeyInfo("企业B", "654321", null, null, null, null, null));
        item.setFiles(null);

        CheckResult checkResult = new CheckResult();
        checkResult.setWebKey("WEB2");
        checkResult.setWebName("网站2");
        checkResult.setStatus("FAIL");
        checkResult.setNote(null);
        checkResult.setResults(Collections.singletonList(item));

        List<CheckResult> checkResults = Collections.singletonList(checkResult);

        when(mockFileService.getFileStreamByFileInfo_forBatch(anyList())).thenReturn(new HashMap<String, InputStream>());

        generator.generateReport(checkResults, tempFile.getAbsolutePath());

        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);
    }

    @Test
    public void testGenerateReport_NoResults() throws Exception {
        CheckResult checkResult = new CheckResult();
        checkResult.setWebKey("WEB3");
        checkResult.setWebName("网站3");
        checkResult.setStatus("SUCCESS");
        checkResult.setNote(null);
        checkResult.setResults(null);

        List<CheckResult> checkResults = Collections.singletonList(checkResult);

        when(mockFileService.getFileStreamByFileInfo_forBatch(anyList())).thenReturn(new HashMap<String, InputStream>());

        generator.generateReport(checkResults, tempFile.getAbsolutePath());

        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);
    }

    @Test
    public void testGenerateReport_ImageInsertException() throws Exception {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileKey("img2");
        fileInfo.setFileName("img2.png");

        ResultItem item = new ResultItem();
        item.setKey(new KeyInfo("企业C", "999999", null, null, null, null, null));
        item.setFiles(Collections.singletonList(fileInfo));

        CheckResult checkResult = new CheckResult();
        checkResult.setWebKey("WEB4");
        checkResult.setWebName("网站4");
        checkResult.setStatus("SUCCESS");
        checkResult.setNote(null);
        checkResult.setResults(Collections.singletonList(item));

        List<CheckResult> checkResults = Collections.singletonList(checkResult);

        // Mock图片流为非法流，触发插入异常
        Map<String, InputStream> fileStreamMap = new HashMap<>();
        fileStreamMap.put("img2", new ByteArrayInputStream(new byte[0]));
        when(mockFileService.getFileStreamByFileInfo_forBatch(anyList())).thenReturn(fileStreamMap);

        generator.generateReport(checkResults, tempFile.getAbsolutePath());

        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);
    }

    @Test
    public void testGetFileStreamMap_Coverage() throws Exception {
        // 多种分支覆盖
        FileInfo fileInfo1 = new FileInfo();
        fileInfo1.setFileKey("k1");
        fileInfo1.setFileName("f1.png");
        FileInfo fileInfo2 = new FileInfo();
        fileInfo2.setFileKey("k2");
        fileInfo2.setFileName("f2.png");

        ResultItem item1 = new ResultItem();
        item1.setFiles(Arrays.asList(fileInfo1, fileInfo2));
        ResultItem item2 = new ResultItem();
        item2.setFiles(null);

        CheckResult checkResult1 = new CheckResult();
        checkResult1.setResults(Arrays.asList(item1, item2));

        CheckResult checkResult2 = new CheckResult();
        checkResult2.setResults(null);

        List<CheckResult> checkResults = Arrays.asList(checkResult1, checkResult2);

        when(mockFileService.getFileStreamByFileInfo_forBatch(anyList())).thenReturn(new HashMap<String, InputStream>());

        Map<String, InputStream> result = invokeGetFileStreamMap(generator, checkResults);
        assertNotNull(result);
    }

    // 反射调用私有方法
    private Map<String, InputStream> invokeGetFileStreamMap(ReportGeneratorV2 generator, List<CheckResult> checkResults) {
        try {
            java.lang.reflect.Method m = ReportGeneratorV2.class.getDeclaredMethod("getFileStreamMap", List.class);
            m.setAccessible(true);
            return (Map<String, InputStream>) m.invoke(generator, checkResults);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 生成一个简单的PNG图片字节流
    private byte[] createFakePng() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // PNG文件头
        baos.write(new byte[]{
                (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A,
                0x00, 0x00, 0x00, 0x0D, 0x49, 0x48, 0x44, 0x52,
                0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01,
                0x08, 0x02, 0x00, 0x00, 0x00, (byte) 0x90, 0x77, 0x53,
                (byte) 0xDE, 0x00, 0x00, 0x00, 0x0A, 0x49, 0x44, 0x41, 0x54,
                0x08, (byte) 0xD7, 0x63, 0x60, 0x00, 0x00, 0x00, 0x02, 0x00,
                0x01, (byte) 0xE2, 0x26, 0x05, (byte) 0x9B, 0x00, 0x00,
                0x00, 0x00, 0x49, 0x45, 0x4E, 0x44, (byte) 0xAE, 0x42,
                0x60, (byte) 0x82
        });
        return baos.toByteArray();
    }
}