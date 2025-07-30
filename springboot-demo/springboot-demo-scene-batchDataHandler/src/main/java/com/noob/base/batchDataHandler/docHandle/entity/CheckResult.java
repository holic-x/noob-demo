package com.noob.base.batchDataHandler.docHandle.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 核查结果
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckResult {
    private String processId;
    private String webKey;
    private String webName;
    private String status;
    private String note;
    private List<ResultItem> results;
}