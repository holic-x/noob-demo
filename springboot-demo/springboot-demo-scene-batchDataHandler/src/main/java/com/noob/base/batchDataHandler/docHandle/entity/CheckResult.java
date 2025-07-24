package com.noob.base.batchDataHandler.docHandle.entity;

import lombok.Data;

import java.util.List;

/**
 * 核查结果
 */
@Data
public class CheckResult {
    private String processId;
    private String webKey;
    private String webName;
    private String status;
    private String note;
    private List<ResultItem> results;
}