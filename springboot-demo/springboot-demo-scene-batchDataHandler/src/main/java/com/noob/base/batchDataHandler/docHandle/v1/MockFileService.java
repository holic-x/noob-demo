package com.noob.base.batchDataHandler.docHandle.v1;

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
        // 模拟服务请求耗时场景
        // mockHandleTime(true);

        // 获取文件流：假设fileKey就是图片文件名（或可做映射）
        String fileName = fileKey + ".png";
        String filePath = baseDir + File.separator + fileName;
        System.out.println("模拟文件处理服务：当前处理图片：" + fileName);
        return new FileInputStream(filePath);
    }

    /**
     * 根据fileName获取图片输入流
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public InputStream getFileStreamByFileName(String fileName) throws Exception {
        // 模拟服务请求耗时场景
        // mockHandleTime(false); // 设定固定耗时验证性能优化场景

        // 获取文件流
        String filePath = baseDir + File.separator + fileName;
        System.out.println("模拟文件处理服务：当前处理图片：" + fileName);
        return new FileInputStream(filePath);
    }

    /**
     * 模拟随机耗时（仿真实文件存储服务场景调用）
     */
    private void mockHandleTime(boolean isRandom) {
        if (isRandom) {
            // 模拟实际场景的随机耗时（如网络/IO延迟）
            int delay = 100 + (int) (Math.random() * 900); // 100~1000毫秒
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                // 忽略中断异常
            }
        } else {
            // 模拟实际场景的固定耗时（如网络/IO延迟）
            int delay = 500; // 此处设定500ms延迟
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                // 忽略中断异常
            }
        }
    }

}