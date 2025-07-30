package com.noob.base.knowledge.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 资源响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResourceResponse extends KnowledgeBaseResponse {
    private ResourceType type;
    private String url;
    private String thumbnailUrl;
    private Metadata metadata;

    public enum ResourceType {
        IMAGE, PDF, VIDEO, AUDIO, OTHER
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {
        private long size;
        private String mimeType;
        private Integer width;
        private Integer height;
        private Integer duration;
        private String sha256;
    }

    public ResourceResponse(ResourceType type, String url, long size) {
        this.type = type;
        this.url = url;
        this.metadata = new Metadata();
        this.metadata.setSize(size);
        this.setSuccess(true);
    }
}