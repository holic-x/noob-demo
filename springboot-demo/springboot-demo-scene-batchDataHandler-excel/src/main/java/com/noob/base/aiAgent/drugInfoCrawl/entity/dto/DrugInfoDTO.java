package com.noob.base.aiAgent.drugInfoCrawl.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 药品基础信息：RPA 侧约定返回（解耦数据对接 和 数据处理）
 * - 数据对接：通过DrugInfoDTOConverter对接数据处理，构建与RPA响应数据的一一映射
 * - 数据处理：对照Excel导出的：getFieldDisplayName（基于实体本身的属性和方法）
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrugInfoDTO {

    // 条目处理状态
    private String itemStatus;

    // 批准文号
    private String approveWord;

    // 产品名称
    private String prdtName;

    // 英文名称
    private String enName;

    // 商品名
    private String commodityName;

    // 剂型
    private String dosageForm;

    // 规格
    private String size;

    // 上市许可持有人
    private String owner;

    // 上市许可持有人地址
    private String ownerLocation;

    // 生产单位
    private String produceCom;

    // 批准日期
    private String approveDate;

    // 生产地址
    private String produceLocation;

    // 产品类别
    private String category;

    // 原批准文号
    private String approveNum;

    // 药品本位码
    private String code;

    // 药品本位码备注
    private String note;

}

/*
// 药品信息实体
    {
      "ApproveNum": "国药准字H20143251",
      "Owner": "龙陵县神龙氧气厂",
      "Category": "化学药品",
      "EnName": "Oxygen",
      "Size": "40L",
      "ApproveWord": "国药准字H20143251",
      "ProduceCom": "龙陵县神龙氧气厂",
      "ProduceLocation": "云南省保山市龙陵县龙新乡茄子山村",
      "prdtName": "氧",
      "OwnerLocation": "云南省保山市龙陵县龙新乡茄子山村",
      "Code": "86909721000026",
      "ApproveDate": "2024-07-19",
      "Note": "",
      "CommodityName": "",
      "DosageForm": "气体"
    }
*/