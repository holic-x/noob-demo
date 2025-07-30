package com.noob.base.knowledge.designMode;

import com.noob.base.knowledge.entity.response.CompositeResponse;
import com.noob.base.knowledge.entity.response.DatabaseEntityResponse;
import com.noob.base.knowledge.entity.response.ESSearchResponse;
import com.noob.base.knowledge.entity.response.KnowledgeBaseResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompositeResponseProcessor extends AbstractResponseProcessor {

    @Override
    protected void validate(RequestContext context) {
        System.out.println("composite validate");
    }

    @Override
    protected Object fetchData(RequestContext context) {
        System.out.println("composite fetch data");
        return null;
    }

    @Override
    protected KnowledgeBaseResponse transform(Object rawData) {
        System.out.println("composite transform");

        // 构建复合响应
        List<DatabaseEntityResponse.KnowledgeArticle> articles = Arrays.asList(
                new DatabaseEntityResponse.KnowledgeArticle(Long.valueOf(1), "Java多态", "关于Java多态的内容", "编程", Arrays.asList("Java", "OOP")),
                new DatabaseEntityResponse.KnowledgeArticle(Long.valueOf(2), "JSON处理", "JSON序列化与反序列化", "编程", Arrays.asList("JSON", "序列化"))
        );
        KnowledgeBaseResponse dbResponse = new DatabaseEntityResponse(articles, 2);

        Map<String, Object> source1 = new HashMap<>();
        source1.put("title", "Java JSON处理");
        source1.put("author", "张三");

        Map<String, Object> source2 = new HashMap<>();
        source2.put("title", "设计模式");
        source2.put("author", "李四");

        List<ESSearchResponse.ESSearchHit> hits = Arrays.asList(
                new ESSearchResponse.ESSearchHit("doc1", "Java中<em>JSON</em>多态实现", 0.95, source1),
                new ESSearchResponse.ESSearchHit("doc2", "<em>多态</em>设计模式", 0.87, source2)
        );

        KnowledgeBaseResponse esResponse = new ESSearchResponse(hits, 15);

        return new CompositeResponse(
                Arrays.asList(dbResponse, esResponse),
                CompositeResponse.CompositeType.MIXED
        );
    }
}