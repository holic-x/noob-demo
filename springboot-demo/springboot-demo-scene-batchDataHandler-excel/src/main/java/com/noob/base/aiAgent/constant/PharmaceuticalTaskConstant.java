package com.noob.base.aiAgent.constant;

/**
 * 【药企信息核查】模块 常量配置说明
 */
public class PharmaceuticalTaskConstant {

    // ------------------------------------------------------------------------------
    // 任务配置相关
    // ------------------------------------------------------------------------------
    public static final String TASK_TYPE = "药品信息爬取";


    // RPA 单次任务发起最大子任务限制
    // public static final Integer BATCH_SUB_RPA_TASK_LIMIT = 500; // 单次RPA任务发起最大子任务限制（切换为apollo配置控制）


    // ------------------------------------------------------------------------------
    // RPA对接状态相关
    // ------------------------------------------------------------------------------
    // RPA 对接任务处理状态
    public static final String RPA_TASK_STATUS_SUCCESS = "SUCCESS"; // 处理成功
    public static final String RPA_TASK_STATUS_FAIL = "FAIL"; // 处理失败

    // RPA 条目/明细 处理状态（药企关联药品信息条目的爬取状态）
    public static final String ITEM_STATUS_SUCCESS = "SUCCESS"; // 条目信息爬取成功
    public static final String ITEM_STATUS_FAIL = "FAIL"; // 条目信息爬取失败




    // ------------------------------------------------------------------------------
    // 关联任务状态相关
    // ------------------------------------------------------------------------------
    // 任务处理状态 对照 t_due_pharmaceutical_task 表 task_status
    public static final Integer TASK_STATUS_HANDLING = 0; // 处理中
    public static final Integer TASK_STATUS_RPA_DONE = 1; // RPA回调完成
    public static final Integer TASK_STATUS_SUCCESS = 2; // 处理成功
    public static final Integer TASK_STATUS_FAILED = 3; // 处理失败



}