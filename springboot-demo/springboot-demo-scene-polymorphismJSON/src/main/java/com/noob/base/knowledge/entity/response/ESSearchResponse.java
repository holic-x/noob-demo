package com.noob.base.knowledge.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * ES 搜索响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ESSearchResponse extends KnowledgeBaseResponse {
    private List<ESSearchHit> hits;
    private ESSearchMeta meta;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ESSearchHit {
        private String id;
        private String highlight;
        private double score;
        private Map<String, Object> source;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ESSearchMeta {
        private long took;
        private boolean timedOut;
        private ShardStats shards;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShardStats {
        private int total;
        private int successful;
        private int skipped;
        private int failed;
    }

    public ESSearchResponse(List<ESSearchHit> hits, long took) {
        this.hits = hits;
        this.meta = new ESSearchMeta();
        this.meta.setTook(took);
        this.meta.setShards(new ShardStats(1, 1, 0, 0));
        this.setSuccess(true);
    }
}