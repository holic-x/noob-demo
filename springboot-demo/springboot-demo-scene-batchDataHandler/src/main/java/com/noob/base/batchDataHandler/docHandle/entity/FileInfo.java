package com.noob.base.batchDataHandler.docHandle.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件实体定义
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private String fileName;
    private String fileKey;
    private String fileType;
    private String transferType;
}