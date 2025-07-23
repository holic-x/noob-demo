package com.noob.base.batchDataHandler.docHandle.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResultItem {
    private KeyInfo key;
    private String createTime;
    private List<FileInfo> files;
}