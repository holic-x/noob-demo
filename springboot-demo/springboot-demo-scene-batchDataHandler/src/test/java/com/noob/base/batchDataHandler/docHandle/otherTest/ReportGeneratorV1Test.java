package com.noob.base.batchDataHandler.docHandle.otherTest;

import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import com.noob.base.batchDataHandler.docHandle.entity.KeyInfo;
import com.noob.base.batchDataHandler.docHandle.entity.ResultItem;
import com.noob.base.batchDataHandler.docHandle.v1.MockFileService;
import com.noob.base.batchDataHandler.docHandle.v1.ReportGeneratorV1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ReportGeneratorV1Test {

    private File tempDir;
    private MockFileService mockFileService;
    private ReportGeneratorV1 reportGenerator;

    @Before
    public void setUp() throws Exception {
        tempDir = new File(System.getProperty("java.io.tmpdir"), "reportgenv1test");
        tempDir.mkdirs();
        mockFileService = mock(MockFileService.class);
        reportGenerator = new ReportGeneratorV1(mockFileService);
    }

    @After
    public void tearDown() throws Exception {
        for (File f : tempDir.listFiles()) {
            f.delete();
        }
        tempDir.delete();
    }

    private FileInfo createTempImage(String name) throws IOException {
        File file = new File(tempDir, name + ".png");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(new byte[]{(byte)137, 80, 78, 71, 13, 10, 26, 10}); // PNG header
        }
        return new FileInfo(name + ".png", name, name, null);
    }

    @Test
    public void testGenerateReport_Normal() throws Exception {
        FileInfo fileInfo = createTempImage("img1");
        when(mockFileService.getFileStreamByFileName(eq("img1.png")))
                .thenReturn(new FileInputStream(new File(tempDir, "img1.png")));

        KeyInfo keyInfo = new KeyInfo("企业A", "123456", null, null, null, null, null);
        ResultItem item = new ResultItem();
        item.setKey(keyInfo);
        item.setFiles(Collections.singletonList(fileInfo));

        CheckResult checkResult = new CheckResult();
        checkResult.setWebKey("WEB1");
        checkResult.setWebName("网站1");
        checkResult.setStatus("SUCCESS");
        checkResult.setNote("备注信息");
        checkResult.setResults(Collections.singletonList(item));

        List<CheckResult> checkResults = Collections.singletonList(checkResult);

        File outFile = new File(tempDir, "report.docx");
        reportGenerator.generateReport(checkResults, outFile.getAbsolutePath());

        assertTrue(outFile.exists());
        // 可进一步用POI读取文档内容校验结构
    }

    @Test
    public void testGenerateReport_NoResults() throws Exception {
        CheckResult checkResult = new CheckResult();
        checkResult.setWebKey("WEB2");
        checkResult.setWebName("网站2");
        checkResult.setStatus("FAIL");
        checkResult.setNote(null);
        checkResult.setResults(null);

        List<CheckResult> checkResults = Collections.singletonList(checkResult);

        File outFile = new File(tempDir, "report2.docx");
        reportGenerator.generateReport(checkResults, outFile.getAbsolutePath());

        assertTrue(outFile.exists());
    }

    @Test
    public void testGenerateReport_NoFiles() throws Exception {
        KeyInfo keyInfo = new KeyInfo("企业B", "654321", null, null, null, null, null);
        ResultItem item = new ResultItem();
        item.setKey(keyInfo);
        item.setFiles(null);

        CheckResult checkResult = new CheckResult();
        checkResult.setWebKey("WEB3");
        checkResult.setWebName("网站3");
        checkResult.setStatus("SUCCESS");
        checkResult.setNote(null);
        checkResult.setResults(Collections.singletonList(item));

        List<CheckResult> checkResults = Collections.singletonList(checkResult);

        File outFile = new File(tempDir, "report3.docx");
        reportGenerator.generateReport(checkResults, outFile.getAbsolutePath());

        assertTrue(outFile.exists());
    }

    @Test
    public void testGenerateReport_ImageInsertException() throws Exception {
        FileInfo fileInfo = createTempImage("img2");
        when(mockFileService.getFileStreamByFileName(eq("img2.png")))
                .thenThrow(new IOException("mock error"));

        KeyInfo keyInfo = new KeyInfo("企业C", "999999", null, null, null, null, null);
        ResultItem item = new ResultItem();
        item.setKey(keyInfo);
        item.setFiles(Collections.singletonList(fileInfo));

        CheckResult checkResult = new CheckResult();
        checkResult.setWebKey("WEB4");
        checkResult.setWebName("网站4");
        checkResult.setStatus("FAIL");
        checkResult.setNote("有异常");
        checkResult.setResults(Collections.singletonList(item));

        List<CheckResult> checkResults = Collections.singletonList(checkResult);

        File outFile = new File(tempDir, "report4.docx");
        reportGenerator.generateReport(checkResults, outFile.getAbsolutePath());

        assertTrue(outFile.exists());
    }

    @Test
    public void testConstructor() {
        assertNotNull(new ReportGeneratorV1(mockFileService));
    }
}