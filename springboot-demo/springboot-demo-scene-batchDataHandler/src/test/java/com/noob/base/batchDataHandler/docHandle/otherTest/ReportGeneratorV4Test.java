package com.noob.base.batchDataHandler.docHandle.otherTest;

import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import com.noob.base.batchDataHandler.docHandle.entity.KeyInfo;
import com.noob.base.batchDataHandler.docHandle.entity.ResultItem;
import com.noob.base.batchDataHandler.docHandle.v2_optimize_batchGet.MockBatchFileService;
import com.noob.base.batchDataHandler.docHandle.v4_optimize_batchAndGM.ReportGeneratorV4;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;

public class ReportGeneratorV4Test {

    private File tempDir;

    @Before
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("reportgenv4test").toFile();
    }

    @After
    public void tearDown() {
        if (tempDir != null && tempDir.exists()) {
            for (File f : tempDir.listFiles()) {
                f.delete();
            }
            tempDir.delete();
        }
    }

    private FileInfo createTempPngFile(String key) throws IOException {
        File file = new File(tempDir, key + ".png");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // 写入最小合法PNG
            fos.write(new byte[]{
                    (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A,
                    0x00, 0x00, 0x00, 0x0D, 0x49, 0x48, 0x44, 0x52,
                    0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01,
                    0x08, 0x02, 0x00, 0x00, 0x00, (byte) 0x90, 0x77, 0x53,
                    (byte) 0xDE, 0x00, 0x00, 0x00, 0x0A, 0x49, 0x44, 0x41,
                    0x54, 0x08, (byte) 0xD7, 0x63, 0x60, 0x00, 0x00, 0x00, 0x02,
                    0x00, 0x01, (byte) 0xE2, 0x26, (byte) 0x05, (byte) 0x9B,
                    0x00, 0x00, 0x00, 0x00, 0x49, 0x45, 0x4E, 0x44,
                    (byte) 0xAE, 0x42, 0x60, (byte) 0x82
            });
        }
        FileInfo fi = new FileInfo();
        fi.setFileKey(key);
        fi.setFileName(key + ".png");
        return fi;
    }

    @Test
    public void testGenerateReport_NormalBatch() throws Exception {
        // 构造数据
        FileInfo fi1 = createTempPngFile("img1");
        FileInfo fi2 = createTempPngFile("img2");

        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setIdNo("1234567890");
        keyInfo.setName("企业A");

        ResultItem item1 = new ResultItem();
        item1.setKey(keyInfo);
        item1.setFiles(Collections.singletonList(fi1));

        ResultItem item2 = new ResultItem();
        item2.setKey(keyInfo);
        item2.setFiles(Collections.singletonList(fi2));

        CheckResult cr1 = new CheckResult();
        cr1.setWebKey("webA");
        cr1.setWebName("网站A");
        cr1.setStatus("SUCCESS");
        cr1.setNote("备注A");
        cr1.setResults(Arrays.asList(item1, item2));

        CheckResult cr2 = new CheckResult();
        cr2.setWebKey("webB");
        cr2.setWebName("网站B");
        cr2.setStatus("FAIL");
        cr2.setNote("备注B");
        cr2.setResults(Collections.singletonList(item1));

        List<CheckResult> checkResults = Arrays.asList(cr1, cr2);

        MockBatchFileService fileService = new MockBatchFileService(tempDir.getAbsolutePath());
        ReportGeneratorV4 generator = new ReportGeneratorV4(fileService);

        String outDir = new File(tempDir, "out").getAbsolutePath();
        generator.generateReport(checkResults, outDir, "final.docx");

        // 检查批次目录和合并文件
        File batchDir = new File(outDir);
        Assert.assertTrue(batchDir.exists());
        File[] files = batchDir.listFiles();
        Assert.assertNotNull(files);
        Assert.assertTrue(files.length > 0);
        boolean foundDocx = false;
        for (File f : files) {
            if (f.getName().endsWith(".docx")) {
                foundDocx = true;
                // 可进一步用POI读取校验内容
            }
        }
        Assert.assertTrue(foundDocx);
    }

    @Test
    public void testGenerateReport_NoResults() throws Exception {
        CheckResult cr = new CheckResult();
        cr.setWebKey("webC");
        cr.setWebName("网站C");
        cr.setStatus("FAIL");
        cr.setNote(null);
        cr.setResults(null);

        List<CheckResult> checkResults = Collections.singletonList(cr);

        MockBatchFileService fileService = new MockBatchFileService(tempDir.getAbsolutePath());
        ReportGeneratorV4 generator = new ReportGeneratorV4(fileService);

        String outDir = new File(tempDir, "out2").getAbsolutePath();
        generator.generateReport(checkResults, outDir, "final2.docx");

        File batchDir = new File(outDir);
        Assert.assertTrue(batchDir.exists());
        File[] files = batchDir.listFiles();
        Assert.assertNotNull(files);
        Assert.assertTrue(files.length > 0);
    }

    @Test
    public void testWriteWithParentCheck() throws Exception {
        MockBatchFileService fileService = new MockBatchFileService(tempDir.getAbsolutePath());
        ReportGeneratorV4 generator = new ReportGeneratorV4(fileService);

        XWPFDocument doc = new XWPFDocument();
        String outPath = tempDir.getAbsolutePath() + File.separator + "subdir" + File.separator + "test.docx";
        ReportGeneratorV4.writeWithParentCheck(doc, outPath);

        File outFile = new File(outPath);
        Assert.assertTrue(outFile.exists());
    }

    @Test
    public void testPartitionHandler_Exception() {
        MockBatchFileService fileService = new MockBatchFileService(tempDir.getAbsolutePath()) {
            @Override
            public Map<String, InputStream> getFileStreamByFileInfo_forBatch(List<FileInfo> fileInfoList) {
                throw new RuntimeException("mock batch fail");
            }
        };
        ReportGeneratorV4 generator = new ReportGeneratorV4(fileService);

        List<CheckResult> checkResults = new ArrayList<>();
        CheckResult cr = new CheckResult();
        cr.setWebKey("webD");
        cr.setWebName("网站D");
        cr.setStatus("SUCCESS");
        cr.setResults(new ArrayList<ResultItem>());
        checkResults.add(cr);

        try {
            generator.generateReport(checkResults, tempDir.getAbsolutePath(), "fail.docx");
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("mock batch fail"));
        }
    }
}