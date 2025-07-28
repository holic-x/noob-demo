package com.noob.base.knowledge.designMode;

import com.noob.base.knowledge.entity.response.DatabaseEntityResponse;
import com.noob.base.knowledge.entity.response.KnowledgeBaseResponse;

import java.util.Arrays;
import java.util.List;

public class DatabaseResponseProcessor extends AbstractResponseProcessor {

    @Override
    protected void validate(RequestContext context) {
        System.out.println("database validate");
    }

    @Override
    protected Object fetchData(RequestContext context) {
        System.out.println("database fetch data");
        return null;
    }

    @Override
    protected KnowledgeBaseResponse transform(Object rawData) {
        System.out.println("database transform");
        // 模拟数据库查询
        List<DatabaseEntityResponse.KnowledgeArticle> articles = Arrays.asList(
                new DatabaseEntityResponse.KnowledgeArticle(Long.valueOf(1), "Java多态", "关于Java多态的内容", "编程", Arrays.asList("Java", "OOP")),
                new DatabaseEntityResponse.KnowledgeArticle(Long.valueOf(2), "JSON处理", "JSON序列化与反序列化", "编程", Arrays.asList("JSON", "序列化"))
        );

        return new DatabaseEntityResponse(articles, 2);
    }
}