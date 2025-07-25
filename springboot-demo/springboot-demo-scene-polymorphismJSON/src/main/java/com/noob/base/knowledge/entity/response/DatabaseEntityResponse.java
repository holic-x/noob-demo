package com.noob.base.knowledge.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 数据库实体响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DatabaseEntityResponse extends KnowledgeBaseResponse {
    private List<KnowledgeArticle> articles;
    private Pagination pagination;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KnowledgeArticle {
        private Long id;
        private String title;
        private String content;
        private String category;
        private List<String> tags;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pagination {
        private int total;
        private int pageSize = 10;
        private int currentPage = 1;
    }

    public DatabaseEntityResponse(List<KnowledgeArticle> articles, int total) {
        this.articles = articles;
        this.pagination = new Pagination();
        pagination.setTotal(total);
        this.setSuccess(true);
    }
}