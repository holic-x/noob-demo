package com.noob.base.batchDataHandler.poiDoc;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CheckItem {
    // 三级核查项标题（如"1.1.1 【测试】-【测试内容1】"）
    private String title;
    // 核查详情子项（如"核查项1：备案信息完整"）
    private List<String> checkDetails = new ArrayList<>();
    // 图片路径列表
    private List<String> imagePaths = new ArrayList<>();
    // 图片字节列表（含占位图）
    private List<byte[]> imageBytesList = new ArrayList<>();

    // 构造器
    public CheckItem(String title) {
        this.title = title;
    }

    // Getter + Setter（全部生成，避免空指针）
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<String> getCheckDetails() { return checkDetails; }
    public void setCheckDetails(List<String> checkDetails) { this.checkDetails = checkDetails; }
    public List<String> getImagePaths() { return imagePaths; }
    public void setImagePaths(List<String> imagePaths) { this.imagePaths = imagePaths; }
    public List<byte[]> getImageBytesList() { return imageBytesList; }
    public void setImageBytesList(List<byte[]> imageBytesList) { this.imageBytesList = imageBytesList; }
}