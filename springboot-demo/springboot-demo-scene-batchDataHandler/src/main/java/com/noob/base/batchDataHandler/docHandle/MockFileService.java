package com.noob.base.batchDataHandler.docHandle;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 模拟文件服务，通过fileKey获取本地图片文件
 */
public class MockFileService {

    private String baseDir;

    public MockFileService(String baseDir) {
        this.baseDir = baseDir;
    }

    /**
     * 根据fileKey获取图片输入流
     *
     * @param fileKey
     * @return
     * @throws Exception
     */
    public InputStream getFileStreamByFileKey(String fileKey) throws Exception {
        // 假设fileKey就是图片文件名（或可做映射）
        String fileName = fileKey + ".png";
        String filePath = baseDir + File.separator + fileName;
        System.out.println("模拟文件处理服务：当前处理图片：" + fileName);
        return new FileInputStream(filePath);
    }

    /**
     * 根据fileName获取图片输入流
     * @param fileName
     * @return
     * @throws Exception
     */
    public InputStream getFileStreamByFileName(String fileName) throws Exception {
        // 假设fileKey就是图片文件名（或可做映射）
        String filePath = baseDir + File.separator + fileName;
        System.out.println("模拟文件处理服务：当前处理图片：" + fileName);
        return new FileInputStream(filePath);
    }
}