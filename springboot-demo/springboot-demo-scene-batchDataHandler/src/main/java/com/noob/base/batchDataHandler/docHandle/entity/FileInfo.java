package com.noob.base.batchDataHandler.docHandle.entity;

import lombok.Data;

@Data
public class FileInfo {
    private String fileName;
    private String fileKey;
    private String fileType;
    private String transferType;
}