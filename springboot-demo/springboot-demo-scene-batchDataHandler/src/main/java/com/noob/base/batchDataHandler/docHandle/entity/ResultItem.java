package com.noob.base.batchDataHandler.docHandle.entity;

import lombok.Data;

import java.util.List;

/**
 * 核查对象结果：包括核查明细、核查文件列表等信息
 */
@Data
public class ResultItem {
    private KeyInfo key;
    private String createTime;
    private List<FileInfo> files;
}