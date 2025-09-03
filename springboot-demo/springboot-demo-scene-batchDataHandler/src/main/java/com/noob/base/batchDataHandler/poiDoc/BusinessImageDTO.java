package com.noob.base.batchDataHandler.poiDoc;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 业务数据DTO（数据传输对象）
 * 用于存储单个业务组的所有信息（三级标题层级结构）
 */


// 轻量核查项类（仅存储标题和图片）


// 主DTO

public class BusinessImageDTO {
    // 一级标题（如"1-1 持续督导工作计划及实施方案"）额外处理
    // private String firstLevelTitle;
    // 二级标题（如"1.百度"）
    private String websiteName;
    // 三级核查项列表（核心嵌套结构）
    private List<CheckItem> checkItems = new ArrayList<>();
    // 处理状态（成功/失败）
    private boolean processSuccess;

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public List<CheckItem> getCheckItems() { return checkItems; }
    public void setCheckItems(List<CheckItem> checkItems) { this.checkItems = checkItems; }
    public boolean isProcessSuccess() { return processSuccess; }
    public void setProcessSuccess(boolean processSuccess) { this.processSuccess = processSuccess; }
}

// 简化方案①
//
//public class BusinessImageDTO {
//    // 一级标题：目录名称（如"1-1 持续督导工作计划及实施方案"）
//    private String firstLevelTitle;
//    // 二级标题：网站名称（如"1.百度"）
//    private String secondLevelTitle;
//    // 三级核查项列表（每个核查项包含标题和多图）
//    // 结构：List<Map<String, Object>> 其中：
//    // - "title" → 核查项标题（如"1.1 【测试】-【测试xxx】"）
//    // - "imagePaths" → 图片路径列表（List<String>）
//    // - "imageBytesList" → 图片字节列表（List<byte[]>，与路径对应）
//    private List<Map<String, Object>> checkItems;
//    // 该二级单元是否处理成功
//    private boolean processSuccess;
//
//    // Getter/Setter
//
//    /**
//     * // 构建核查项
//     * Map<String, Object> checkItem = new HashMap<>();
//     * checkItem.put("title", "1.1 【测试】-【测试xxx】");
//     * checkItem.put("imagePaths", Arrays.asList("path1.png", "path2.png"));
//     * checkItem.put("imageBytesList", Arrays.asList(bytes1, bytes2));
//     *
//     * // 添加到DTO
//     * BusinessImageDTO dto = new BusinessImageDTO();
//     * dto.setFirstLevelTitle("1-1 持续督导工作计划及实施方案");
//     * dto.setSecondLevelTitle("1.百度");
//     * dto.setCheckItems(Arrays.asList(checkItem));
//     */
//}



/*
// 轻量核查项类（仅存储标题和图片）
public class CheckItem {
    private String title; // 核查项标题（三级标题）
    private List<String> imagePaths; // 图片路径列表
    private List<byte[]> imageBytesList; // 图片字节列表

    // 构造器+Getter/Setter（简洁起见可使用Lombok的@Data）
    public CheckItem(String title) {
        this.title = title;
        this.imagePaths = new ArrayList<>();
        this.imageBytesList = new ArrayList<>();
    }
}

// 主DTO
public class BusinessImageDTO {
    private String firstLevelTitle; // 一级标题
    private String secondLevelTitle; // 二级标题
    private List<CheckItem> checkItems = new ArrayList<>(); // 三级核查项列表
    private boolean processSuccess; // 处理状态

    // Getter/Setter
}

 */