package com.noob.base.knowledge.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompositeResponse extends KnowledgeBaseResponse {
    private List<KnowledgeBaseResponse> components;
    private CompositeType compositeType;

    public enum CompositeType {
        MIXED, 
        PRIMARY_WITH_RELATED, 
        SEARCH_WITH_SUGGESTIONS
    }

    public CompositeResponse(List<KnowledgeBaseResponse> components, CompositeType type) {
        this.components = components;
        this.compositeType = type;
        this.setSuccess(true);
    }
}