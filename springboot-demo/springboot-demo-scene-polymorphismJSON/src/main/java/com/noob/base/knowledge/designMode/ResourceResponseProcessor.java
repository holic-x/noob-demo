package com.noob.base.knowledge.designMode;

import com.noob.base.knowledge.entity.response.KnowledgeBaseResponse;
import com.noob.base.knowledge.entity.response.ResourceResponse;

public class ResourceResponseProcessor extends AbstractResponseProcessor {

    @Override
    protected void validate(RequestContext context) {
        System.out.println("resource validate");
    }

    @Override
    protected Object fetchData(RequestContext context) {
        System.out.println("resource fetch data");
        return null;
    }

    @Override
    protected KnowledgeBaseResponse transform(Object rawData) {
        System.out.println("resource transform");

        // 模拟资源获取
        ResourceResponse response = new ResourceResponse(
                ResourceResponse.ResourceType.PDF,
                "/resources/hello.pdf",
                1024 * 1024 * 2
        );
        response.getMetadata().setMimeType("application/pdf");
        return response;
    }
}