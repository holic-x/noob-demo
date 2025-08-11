package com.noob.base.batchDataHandler.docHandle.otherTest;

import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import com.noob.base.batchDataHandler.docHandle.entity.KeyInfo;
import com.noob.base.batchDataHandler.docHandle.entity.ResultItem;
import com.noob.base.batchDataHandler.docHandle.v3_optimize_multiThread.FileFetcher;
import com.noob.base.batchDataHandler.docHandle.v3_optimize_multiThread.ReportGeneratorV3;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReportGeneratorV3Test {

    private File tempDir;

    @Before
    public void setUp() {
        tempDir = new File(System.getProperty("java.io.tmpdir"), "reportgenv3test");
        if (!tempDir.exists()) tempDir.mkdirs();
    }

    @After
    public void tearDown() {
        for (File f : tempDir.listFiles()) {
            f.delete();
        }
        tempDir.delete();
    }

    private FileInfo createTestImageFile(String fileKey, String fileName) throws IOException {
        File img = new File(tempDir, fileName);
        try (OutputStream os = new FileOutputStream(img)) {
            // 写入简单PNG头部，足以被POI识别
            os.write(new byte[]{
                    (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A,
                    0x00, 0x00, 0x00, 0x0D, 0x49, 0x48, 0x44, 0x52,
                    0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01,
                    0x08, 0x02, 0x00, 0x00, 0x00, (byte) 0x90, 0x77, 0x53,
                    (byte) 0xDE, 0x00, 0x00, 0x00, 0x0A, 0x49, 0x44, 0x41, 0x54,
                    0x08, (byte) 0xD7, 0x63, 0x60, 0x00, 0x00, 0x00, 0x02,
                    0x00, 0x01, (byte) 0xE2, 0x26, 0x05, (byte) 0x9B,
                    0x00, 0x00, 0x00, 0x00, 0x49, 0x45, 0x4E, 0x44,
                    (byte) 0xAE, 0x42, 0x60, (byte) 0x82
            });
        }
        FileInfo fi = new FileInfo();
        fi.setFileKey(fileKey);
        fi.setFileName(fileName);
        return fi;
    }

    @Test
    public void testGenerateReport_Normal() throws Exception {
        // 构造数据
        FileInfo fi = createTestImageFile("img1", "img1.png");
        ResultItem item = new ResultItem();
        item.setKey(KeyInfo.builder()
                .name("企业A").idNo("123456")
                .build());
        item.setFiles(Collections.singletonList(fi));
        CheckResult cr = new CheckResult();
        cr.setWebKey("web1");
        cr.setWebName("网站1");
        cr.setStatus("SUCCESS");
        cr.setNote("备注");
        cr.setResults(Collections.singletonList(item));

        List<CheckResult> checkResults = Collections.singletonList(cr);

        FileFetcher fetcher = new FileFetcher(tempDir.getAbsolutePath(), 2);
        ReportGeneratorV3 generator = new ReportGeneratorV3(fetcher);

        File outFile = new File(tempDir, "report.docx");
        generator.generateReport(checkResults, outFile.getAbsolutePath());

        Assert.assertTrue(outFile.exists());
        // 可用POI进一步校验文档内容
    }

    @Test
    public void testGenerateReport_NoResults() throws Exception {
        CheckResult cr = new CheckResult();
        cr.setWebKey("web2");
        cr.setWebName("网站2");
        cr.setStatus("FAIL");
        cr.setNote(null);
        cr.setResults(null);

        List<CheckResult> checkResults = Collections.singletonList(cr);

        FileFetcher fetcher = new FileFetcher(tempDir.getAbsolutePath(), 2);
        ReportGeneratorV3 generator = new ReportGeneratorV3(fetcher);

        File outFile = new File(tempDir, "report2.docx");
        generator.generateReport(checkResults, outFile.getAbsolutePath());

        Assert.assertTrue(outFile.exists());
    }

    @Test
    public void testGenerateReport_ImageNotFound() throws Exception {
        FileInfo fi = new FileInfo();
        fi.setFileKey("notfound");
        fi.setFileName("notfound.png");
        ResultItem item = new ResultItem();
        item.setKey(KeyInfo.builder()
                .name("企业B").idNo("654321")
                .build());
        item.setFiles(Collections.singletonList(fi));
        CheckResult cr = new CheckResult();
        cr.setWebKey("web3");
        cr.setWebName("网站3");
        cr.setStatus("SUCCESS");
        cr.setNote(null);
        cr.setResults(Collections.singletonList(item));

        List<CheckResult> checkResults = Collections.singletonList(cr);

        FileFetcher fetcher = new FileFetcher(tempDir.getAbsolutePath(), 2);
        ReportGeneratorV3 generator = new ReportGeneratorV3(fetcher);

        File outFile = new File(tempDir, "report3.docx");
        generator.generateReport(checkResults, outFile.getAbsolutePath());

        Assert.assertTrue(outFile.exists());
    }

    @Test
    public void testGenerateReport_MultiWebsites() throws Exception {
        FileInfo fi1 = createTestImageFile("img2", "img2.png");
        FileInfo fi2 = createTestImageFile("img3", "img3.png");
        ResultItem item1 = new ResultItem();
        item1.setKey(KeyInfo.builder()
                .name("企业C").idNo("111111")
                .build());
        item1.setFiles(Collections.singletonList(fi1));
        ResultItem item2 = new ResultItem();
        item2.setKey(KeyInfo.builder()
                .name("企业D").idNo("222222")
                .build());
        item2.setFiles(Collections.singletonList(fi2));
        CheckResult cr1 = new CheckResult();
        cr1.setWebKey("web4");
        cr1.setWebName("网站4");
        cr1.setStatus("SUCCESS");
        cr1.setNote("备注1");
        cr1.setResults(Collections.singletonList(item1));
        CheckResult cr2 = new CheckResult();
        cr2.setWebKey("web5");
        cr2.setWebName("网站5");
        cr2.setStatus("FAIL");
        cr2.setNote("备注2");
        cr2.setResults(Collections.singletonList(item2));

        List<CheckResult> checkResults = Arrays.asList(cr1, cr2);

        FileFetcher fetcher = new FileFetcher(tempDir.getAbsolutePath(), 2);
        ReportGeneratorV3 generator = new ReportGeneratorV3(fetcher);

        File outFile = new File(tempDir, "report4.docx");
        generator.generateReport(checkResults, outFile.getAbsolutePath());

        Assert.assertTrue(outFile.exists());
    }
}