package com.noob.base.demo.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    // private static final String UPLOAD_DIR = "uploads/";
    private static final String UPLOAD_DIR = "D:\\Desktop\\test\\upload\\storage\\"; // 设置文件上传保存路径

    /**
     * 上传分片
     *
     * @param file     分片文件
     * @param chunk    当前分片索引
     * @param chunks   总分片数
     * @param fileName 文件名
     * @param fileMd5  文件的 MD5 值（用于标识文件）
     */
    @PostMapping("/chunk")
    public String uploadChunk(@RequestParam("file") MultipartFile file,
                              @RequestParam("chunk") int chunk,
                              @RequestParam("chunks") int chunks,
                              @RequestParam("fileName") String fileName,
                              @RequestParam("fileMd5") String fileMd5) throws IOException {
        // 创建上传目录
        File uploadDir = new File(UPLOAD_DIR + fileMd5);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 保存分片文件
        File chunkFile = new File(uploadDir, chunk + ".part");
        file.transferTo(chunkFile);

        return "Chunk uploaded: " + chunk;
    }

    /**
     * 合并分片
     *
     * @param fileName 文件名
     * @param fileMd5  文件的 MD5 值
     */
    @PostMapping("/merge")
    public String mergeChunks(@RequestParam("fileName") String fileName,
                              @RequestParam("fileMd5") String fileMd5) throws IOException {
        File uploadDir = new File(UPLOAD_DIR + fileMd5);
        File[] chunks = uploadDir.listFiles();

        // 创建目标文件
        File targetFile = new File(UPLOAD_DIR + fileName);
        for (File chunk : chunks) {
            // 将分片内容追加到目标文件
            FileUtils.writeByteArrayToFile(targetFile, FileUtils.readFileToByteArray(chunk), true);
            // 删除分片文件
            chunk.delete();
        }

        // 删除分片目录
        uploadDir.delete();

        return "File merged: " + fileName;
    }
}