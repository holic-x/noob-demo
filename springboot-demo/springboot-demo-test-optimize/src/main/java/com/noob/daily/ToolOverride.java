package com.noob.daily;

import java.util.Objects;

/**
 * 工具类覆盖实体类
 * 用于管理工具类的覆盖和扩展
 */
public class ToolOverride {
    
    // 工具类名称
    private String toolClassName;
    
    // 原始工具类全限定名
    private String originalClass;
    
    // 覆盖工具类全限定名
    private String overrideClass;
    
    // 覆盖优先级（数值越大优先级越高）
    private int priority;
    
    // 是否启用覆盖
    private boolean enabled;
    
    // 覆盖描述
    private String description;
    
    // 覆盖配置参数（JSON格式）
    private String configParams;
    
    // 创建时间
    private long createTime;
    
    // 更新时间
    private long updateTime;

    // 默认构造函数
    public ToolOverride() {
        this.createTime = System.currentTimeMillis();
        this.updateTime = this.createTime;
    }

    // 全参数构造函数
    public ToolOverride(String toolClassName, String originalClass, String overrideClass, 
                       int priority, boolean enabled, String description, String configParams) {
        this();
        this.toolClassName = toolClassName;
        this.originalClass = originalClass;
        this.overrideClass = overrideClass;
        this.priority = priority;
        this.enabled = enabled;
        this.description = description;
        this.configParams = configParams;
    }

    // Getters and Setters
    public String getToolClassName() {
        return toolClassName;
    }

    public void setToolClassName(String toolClassName) {
        this.toolClassName = toolClassName;
        this.updateTime = System.currentTimeMillis();
    }

    public String getOriginalClass() {
        return originalClass;
    }

    public void setOriginalClass(String originalClass) {
        this.originalClass = originalClass;
        this.updateTime = System.currentTimeMillis();
    }

    public String getOverrideClass() {
        return overrideClass;
    }

    public void setOverrideClass(String overrideClass) {
        this.overrideClass = overrideClass;
        this.updateTime = System.currentTimeMillis();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
        this.updateTime = System.currentTimeMillis();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        this.updateTime = System.currentTimeMillis();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updateTime = System.currentTimeMillis();
    }

    public String getConfigParams() {
        return configParams;
    }

    public void setConfigParams(String configParams) {
        this.configParams = configParams;
        this.updateTime = System.currentTimeMillis();
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    // equals和hashCode方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToolOverride that = (ToolOverride) o;
        return priority == that.priority && 
               enabled == that.enabled && 
               Objects.equals(toolClassName, that.toolClassName) && 
               Objects.equals(originalClass, that.originalClass) && 
               Objects.equals(overrideClass, that.overrideClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toolClassName, originalClass, overrideClass, priority, enabled);
    }

    // toString方法
    @Override
    public String toString() {
        return "ToolOverride{" +
                "toolClassName='" + toolClassName + '\'' +
                ", originalClass='" + originalClass + '\'' +
                ", overrideClass='" + overrideClass + '\'' +
                ", priority=" + priority +
                ", enabled=" + enabled +
                ", description='" + description + '\'' +
                ", configParams='" + configParams + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}