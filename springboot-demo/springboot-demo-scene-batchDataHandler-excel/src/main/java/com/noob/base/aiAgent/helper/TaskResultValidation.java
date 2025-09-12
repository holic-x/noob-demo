package com.noob.base.aiAgent.helper;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * RPA批次任务处理结果校验器
 * 校验结果封装类：泛型处理（校验数据列表，返回校验信息&封装列表）
 */
// @Data 切换复合注解，针对场景只需要Getter
@Getter
public class TaskResultValidation<T> {

    // 处理成功
    private boolean success;

    // 校验消息（批次任务执行成功时补充信息提示，失败时使用补充失败校验信息）
    private String message;

    // 执行成功的实体列表
    private final List<T> successList;

    // 执行失败的实体列表
    private final List<T> failList;

    // 私有构造方法
    private TaskResultValidation(boolean success, String message,
                                 List<T> successEntities, List<T> failEntities) {
        this.success = success;
        this.message = message;
        this.successList = successEntities != null ? new ArrayList<>(successEntities) : new ArrayList<>();
        this.failList = failEntities != null ? new ArrayList<>(failEntities) : new ArrayList<>();
    }

    /**
     * 创建校验成功的返回结果(只返回校验信息，不返回相关数据列表)
     *
     * @return 校验结果
     */
    public static <T> TaskResultValidation<T> allSuccess() {
        return new TaskResultValidation<>(true, "任务执行全部成功", null, null);
    }

    /**
     * 创建校验所有状态都返回执行成功的返回结果
     *
     * @param successEntities 成功的实体列表
     * @return 校验结果
     */
    public static <T> TaskResultValidation<T> allSuccess(List<T> successEntities, String message) {
        return new TaskResultValidation<>(true, String.format("任务执行全部成功：%s", message), successEntities, null);
    }

    /**
     * 创建部分成功的校验结果（部分成功，当做成功的完结状态处理）
     *
     * @param successEntities 成功的实体列表
     * @param failEntities    失败的实体列表
     * @param message         提示信息
     * @return 校验结果
     */
    public static <T> TaskResultValidation<T> partialSuccess(List<T> successEntities,
                                                             List<T> failEntities,
                                                             String message) {
        return new TaskResultValidation<>(true, String.format("任务执行部分成功：%s", message), successEntities, failEntities);
    }


    /**
     * 创建校验失败的返回结果(只返回校验信息，不返回相关数据列表)
     *
     * @param message 错误信息
     * @return 校验结果
     */
    public static <T> TaskResultValidation<T> failure(String message) {
        return new TaskResultValidation<>(false, String.format("任务执行全部失败：%s", message), null, null);
    }

    /**
     * 创建校验失败的返回结果
     *
     * @param failEntities 校验失败的实体列表
     * @param message      错误信息
     *                     // @param unknownStatusEntities 未知状态的实体列表
     * @return 校验结果
     */
    public static <T> TaskResultValidation<T> failure(List<T> failEntities,
                                                      String message) {
        return new TaskResultValidation<>(false, String.format("任务执行全部失败：%s", message), null, failEntities);
    }

    /**
     * 获取所有数据实体(成功状态+失败状态+未知的对接状态)
     */
    public List<T> getAllEntities() {
        List<T> all = new ArrayList<>(successList);
        all.addAll(failList);
        return all;
    }

    @Override
    public String toString() {
        return "TaskResultValidation{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", successList=" + successList +
                ", failList=" + failList +
                '}';
    }

}