package com.noob.base.batchDataHandler.docHandle.skill;

import lombok.extern.slf4j.Slf4j;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
public class DocxUtilTest {

    @Test
    public void test_createDocx() throws Exception {

        String targetFilePath = "D:\\25535\\coremem--董监高及核心技术人员\\1-1-3\\250522_test001.docx";
        // String targetFilePath = "D:\\25535\\1-1-3_尽调底稿违法违规生成_2025-05-14_1747204745281.docx"; // 如果目录不存在需手动创建避免异常

        File file = new File(targetFilePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }


        String catalogName = "底稿目录名称";
        WordprocessingMLPackage wordMlPackage = DocxUtil.createDocument();

        // 加底稿目录名称
        DocxUtil.addParagraphTextWithStyle(wordMlPackage, "", DocxUtil.HEADING1);
        DocxUtil.addParagraphTextWithStyle(wordMlPackage, catalogName, DocxUtil.HEADING1);

        int imageCount = 0;

        /**
         * 文件数据不存在，下载数据失败
         * java.lang.RuntimeException: can't find fileId 1715763385694_000_000_998672A92EEBD7BDD61F00CF62F8C7FCBA8CBC9D5B418677B5D33E603A9338D9 from database
         *
         * 	at com.htsc.paas.sdk.storage.utils.Downloader.handleStatus(Downloader.java:112)
         * 	at com.htsc.paas.sdk.storage.utils.Downloader.getFile(Downloader.java:101)
         * 	at com.htsc.paas.sdk.storage.client.SimpleClient.getFile(SimpleClient.java:115)
         * 	at com.htsc.ione.duediligence.dueExt.job.DueProcessTaskNewJobForExecute_CreateDocxTest.testCreateDocx(DueProcessTaskNewJobForExecute_CreateDocxTest.java:51)
         */
        // String fileKey = "1747791706290_000_000_C2C17D0011CC4361BE76D0C9A31801BB87B6269631DA626CA68AF54CB133CD99";
        // String fileKey = "1715763385694_000_000_998672A92EEBD7BDD61F00CF62F8C7FCBA8CBC9D5B418677B5D33E603A9338D9"; // 无效的fileKey
        String fileKey = "1748229519046_000_000_7C2E9C4DD8025DB47E64DAE0BD0A2AA8037EFB83CFC4F67F29175E64A8828BC3"; // 有效的fileKey
        log.info("开始下载：fileId={}", fileKey);

        /*
        String url = "168.61.9.1:12006"; // dueDiligence.fileUrl
        StorageClient client = Storage.create(url);
        FileEntity fileEntity = client.getFile(fileKey);
        byte[] imgBytes = fileEntity.getBytes();
        double ratio = 1.2;
        InputStream is = new ByteArrayInputStream(imgBytes);
         */

        double ratio = 1.2;


        //  InputStream is = new ByteArrayInputStream(imgBytes);
        InputStream is = new FileInputStream(new File("E:\\test\\test-files\\batch_test_ver02\\template.png"));

        BufferedImage image = ImageIO.read(is);
        int width = image.getWidth();
        int height = image.getHeight();
        int newWidth = width;
        int newHeight = (int) (newWidth * ratio);
        if (newHeight > height) {
            newHeight = height;
        }
        image = image.getSubimage(0, 0, newWidth, newHeight);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imgBytes2 = baos.toByteArray();
        DocxUtil.addImage(wordMlPackage, imgBytes2, ++imageCount, null);


        DocxUtil.initTocStyle(wordMlPackage);
        DocxUtil.addToc(wordMlPackage, 1, "TOC \\o \"1-3\" \\h \\z \\u ");
        log.info("targetFilePath1:::{}", targetFilePath);
        DocxUtil.saveDocument(wordMlPackage, targetFilePath);
        log.info("生成文档成功！");

    }
}
