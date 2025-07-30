package com.noob.base.knowledge.controller;

import com.noob.base.knowledge.entity.response.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @GetMapping("/database")
    public KnowledgeBaseResponse getDatabaseEntities(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        // 模拟数据库查询
        List<DatabaseEntityResponse.KnowledgeArticle> articles = Arrays.asList(
                new DatabaseEntityResponse.KnowledgeArticle(Long.valueOf(1), "Java多态", "关于Java多态的内容", "编程", Arrays.asList("Java", "OOP")),
                new DatabaseEntityResponse.KnowledgeArticle(Long.valueOf(2), "JSON处理", "JSON序列化与反序列化", "编程", Arrays.asList("JSON", "序列化"))
        );

        return new DatabaseEntityResponse(articles, 2);
    }

    @GetMapping("/search")
    public KnowledgeBaseResponse search(@RequestParam String keyword) {
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

    @GetMapping("/resource/{id}")
    public KnowledgeBaseResponse getResource(@PathVariable String id) {
        // 模拟资源获取
        ResourceResponse response = new ResourceResponse(
                ResourceResponse.ResourceType.PDF,
                "/resources/" + id + ".pdf",
                1024 * 1024 * 2
        );
        response.getMetadata().setMimeType("application/pdf");
        return response;
    }

    @GetMapping("/composite")
    public KnowledgeBaseResponse getCompositeData() {
        // 构建复合响应
        KnowledgeBaseResponse dbResponse = getDatabaseEntities(1, 10);
        KnowledgeBaseResponse esResponse = search("java");

        return new CompositeResponse(
                Arrays.asList(dbResponse, esResponse),
                CompositeResponse.CompositeType.MIXED
        );
    }
}