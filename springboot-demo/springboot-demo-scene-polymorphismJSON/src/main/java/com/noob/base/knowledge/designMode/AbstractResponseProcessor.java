package com.noob.base.knowledge.designMode;

import com.noob.base.knowledge.entity.response.KnowledgeBaseResponse;


/**
 * 模板方法基类
 */
public abstract class AbstractResponseProcessor {
    // 模板方法（final防止子类修改算法结构）
    public final KnowledgeBaseResponse process(RequestContext context) {
        // 1. 参数验证
        validate(context);

        // 2. 获取原始数据
        Object rawData = fetchData(context);

        // 3. 数据转换
        KnowledgeBaseResponse response = transform(rawData);

        // 4. 响应增强
        enrichResponse(response, context);

        return response;
    }

    // 抽象方法 - 必须由子类实现
    protected abstract void validate(RequestContext context);

    protected abstract Object fetchData(RequestContext context);

    protected abstract KnowledgeBaseResponse transform(Object rawData);

    // 钩子方法 - 子类可选重写
    protected void enrichResponse(KnowledgeBaseResponse response, RequestContext context) {
        // 默认实现：添加通用元数据
        response.setRequestId(context.getRequestId());
        response.setTimestamp(System.currentTimeMillis());
        response.setApiVersion("1.0.0");
    }
}