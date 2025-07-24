package com.noob.base.batchDataHandler.docHandle.entity;

import lombok.Data;

/**
 * 文件实体定义
 */
@Data
public class FileInfo {
    private String fileName;
    private String fileKey;
    private String fileType;
    private String transferType;
}