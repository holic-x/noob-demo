package com.noob.base.batchDataHandler.docHandle.entity;

import lombok.Data;

/**
 * 核查对象明细（核查结果信息）
 */
@Data
public class KeyInfo {
    private String name;
    private String idNo;
    private String type;
    private String label;
    private String location;
    private String webKeys;
    private String personList;
}