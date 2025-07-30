package com.noob.base.knowledge.designMode;

import com.noob.base.knowledge.entity.response.ESSearchResponse;
import com.noob.base.knowledge.entity.response.KnowledgeBaseResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESSearchResponseProcessor extends AbstractResponseProcessor {

    @Override
    protected void validate(RequestContext context) {
        System.out.println("es validate");
    }

    @Override
    protected Object fetchData(RequestContext context) {
        System.out.println("es fetchData");
        return null;
    }

    @Override
    protected KnowledgeBaseResponse transform(Object rawData) {

        System.out.println("es transform");
        // 模拟ES搜索
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

        return new ESSearchResponse(hits, 15);
    }
}