package com.noob.base.knowledge.entity.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础响应类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "dataType"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DatabaseEntityResponse.class, name = "DATABASE"),
    @JsonSubTypes.Type(value = ESSearchResponse.class, name = "ES_SEARCH"),
    @JsonSubTypes.Type(value = ResourceResponse.class, name = "RESOURCE"),
    @JsonSubTypes.Type(value = CompositeResponse.class, name = "COMPOSITE")
})
public abstract class KnowledgeBaseResponse {
    private boolean success;
    private String requestId;
    private long timestamp = System.currentTimeMillis();
    private String apiVersion = "1.0.0";
}